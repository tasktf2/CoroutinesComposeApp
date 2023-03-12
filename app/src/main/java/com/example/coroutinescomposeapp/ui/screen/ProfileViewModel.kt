package com.example.coroutinescomposeapp.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.coroutinescomposeapp.di.TempData
import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.usecase.DeleteUserByEmailUseCase
import com.example.coroutinescomposeapp.domain.usecase.GetUserByEmailUseCase
import com.example.coroutinescomposeapp.ui.model.UserUI
import kotlinx.coroutines.launch

sealed class ProfileState {
    object Loading : ProfileState()
    data class Success(val userUI: UserUI) : ProfileState()
    object Logout : ProfileState()
}

class ProfileViewModel(
    val getOwnUserEmailUseCase: UseCase<Unit, String>,
    val getUserByEmailUseCase: UseCase<GetUserByEmailUseCase.Params, UserUI>,
    val deleteUserByEmailUseCase: UseCase<DeleteUserByEmailUseCase.Params, Unit>
) : ViewModel() {

    var uiState: ProfileState by mutableStateOf(ProfileState.Loading)
        private set

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            val email = getOwnUserEmailUseCase.execute(Unit)
            val user = getUserByEmailUseCase.execute(GetUserByEmailUseCase.Params(email))
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

    companion object {
        val factory = viewModelFactory {
            initializer {
                ProfileViewModel(
                    getOwnUserEmailUseCase = TempData.getOwnUserEmailUseCase,
                    getUserByEmailUseCase = TempData.getUserByEmailUseCase,
                    deleteUserByEmailUseCase = TempData.deleteUserByEmailUseCase
                )
            }
        }
    }
}