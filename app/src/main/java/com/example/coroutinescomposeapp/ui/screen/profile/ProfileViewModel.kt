package com.example.coroutinescomposeapp.ui.screen.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinescomposeapp.ui.model.UserUI
import com.example.coroutinescomposeapp.ui.model.toUi
import com.example.domain.base.UseCase
import com.example.domain.model.User
import com.example.domain.usecase.DeleteUserByEmailUseCase
import com.example.domain.usecase.GetUserByEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ProfileState {
    object Loading : ProfileState()
    data class Success(val userUI: UserUI) : ProfileState()
    object Logout : ProfileState()
}

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getOwnUserEmailUseCase: @JvmSuppressWildcards UseCase<Unit, String>,
    private val getUserByEmailUseCase: @JvmSuppressWildcards UseCase<GetUserByEmailUseCase.Params, User>,
    private val deleteUserByEmailUseCase: @JvmSuppressWildcards UseCase<DeleteUserByEmailUseCase.Params, Unit>
) : ViewModel() {

    var uiState: ProfileState by mutableStateOf(ProfileState.Loading)
        private set

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            val email = getOwnUserEmailUseCase.execute(Unit)
            val user = getUserByEmailUseCase.execute(GetUserByEmailUseCase.Params(email)).toUi()
            uiState = ProfileState.Success(user)
        }
    }

    fun logout() {
        deleteUser()
        uiState = ProfileState.Logout
    }

    private fun deleteUser() {
        viewModelScope.launch {
            val email = getOwnUserEmailUseCase.execute(Unit)
            deleteUserByEmailUseCase.execute(DeleteUserByEmailUseCase.Params(email))
        }
    }
}