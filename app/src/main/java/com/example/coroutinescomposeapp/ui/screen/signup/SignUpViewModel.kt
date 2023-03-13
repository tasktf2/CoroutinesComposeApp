package com.example.coroutinescomposeapp.ui.screen.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinescomposeapp.ui.model.UserUI
import com.example.coroutinescomposeapp.ui.model.toDomain
import com.example.domain.base.UseCase
import com.example.domain.usecase.CheckIfUserExistsByEmailUseCase
import com.example.domain.usecase.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class SignUpState(open val userUI: UserUI) {
    object Initial : SignUpState(UserUI())
    data class Active(override val userUI: UserUI) : SignUpState(userUI)
    data class Error(override val userUI: UserUI) : SignUpState(userUI)
    data class EmptyEmail(override val userUI: UserUI) : SignUpState(userUI)
    data class UserExists(override val userUI: UserUI) : SignUpState(userUI)
    object Success : SignUpState(UserUI())
    object ToLogin : SignUpState(UserUI())
}

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val insertUserUseCase: @JvmSuppressWildcards UseCase<InsertUserUseCase.Params, Unit>,
    private val checkIfUserExistsByEmailUseCase: @JvmSuppressWildcards UseCase<CheckIfUserExistsByEmailUseCase.Params, Boolean>
) : ViewModel() {

    var uiState by mutableStateOf<SignUpState>(SignUpState.Initial)
        private set


    fun emailChanged(email: String) {
        val currentUser = uiState.userUI

        uiState = SignUpState.Active(
            userUI = currentUser.copy(email = email.lowercase())
        )
    }

    fun firstNameChanged(firstName: String) {
        val currentUser = uiState.userUI

        uiState = SignUpState.Active(
            userUI = currentUser.copy(firstName = firstName)
        )
    }

    fun secondNameChanged(secondName: String) {
        val currentUser = uiState.userUI

        uiState = SignUpState.Active(
            userUI = currentUser.copy(secondName = secondName)
        )
    }

    fun signUpButtonClicked(password: String) {

        val currentUser = uiState.userUI
        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")

        viewModelScope.launch {
            uiState = when {
                currentUser.email.isBlank() -> SignUpState.EmptyEmail(currentUser)
                !emailRegex.matches(currentUser.email) -> SignUpState.Error(currentUser)
                else -> {
                    val userExists = checkIfUserExistsByEmailUseCase.execute(
                        CheckIfUserExistsByEmailUseCase.Params(uiState.userUI.email)
                    )
                    Log.d("xxx", "userExists: $userExists")
                    if (userExists) {
                        SignUpState.UserExists(currentUser)
                    } else {
                        insertUserUseCase.execute(
                            InsertUserUseCase.Params(
                                uiState.userUI.copy(
                                    password = password
                                ).toDomain()
                            )
                        )
                        SignUpState.Success
                    }
                }
            }
        }
    }

    fun loginButtonClicked() {
        uiState = SignUpState.ToLogin
    }
}