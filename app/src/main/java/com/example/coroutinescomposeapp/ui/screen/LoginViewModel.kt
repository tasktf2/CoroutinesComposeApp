package com.example.coroutinescomposeapp.ui.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.coroutinescomposeapp.di.TempData
import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.usecase.CheckIfUserExistsByEmailUseCase
import com.example.coroutinescomposeapp.domain.usecase.GetUserByEmailUseCase
import com.example.coroutinescomposeapp.domain.usecase.InsertOwnUserEmailUseCase
import com.example.coroutinescomposeapp.ui.model.UserUI
import kotlinx.coroutines.launch

sealed class LoginState(open val userUI: UserUI) {
    object Initial : LoginState(UserUI())
    data class Active(override val userUI: UserUI) : LoginState(userUI)
    data class WrongEmailError(override val userUI: UserUI) : LoginState(userUI)
    data class WrongPasswordError(override val userUI: UserUI) : LoginState(userUI)
    data class EmptyEmailOrPassword(override val userUI: UserUI) : LoginState(userUI)
    data class UserNotExists(override val userUI: UserUI) : LoginState(userUI)
    object Success : LoginState(UserUI())
}

class LoginViewModel(
    val getUserByEmailUseCase: UseCase<GetUserByEmailUseCase.Params, UserUI>,
    val checkIfUserExistsByEmailUseCase: UseCase<CheckIfUserExistsByEmailUseCase.Params, Boolean>,
    val insertOwnUserEmailUseCase: UseCase<InsertOwnUserEmailUseCase.Params, Unit>
) : ViewModel() {

    var uiState by mutableStateOf<LoginState>(LoginState.Initial)
        private set


    fun emailChanged(email: String) {
        val currentUser = uiState.userUI

        uiState = LoginState.Active(
            userUI = currentUser.copy(email = email.lowercase())
        )
    }
    fun passwordChanged(password: String) {
        val currentUser = uiState.userUI

        uiState = LoginState.Active(
            userUI = currentUser.copy(password = password)
        )
    }

    fun loginButtonClicked() {

        val currentUser = uiState.userUI
        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$")

        viewModelScope.launch {
            uiState = when {
                currentUser.email.isBlank() || currentUser.password.isBlank() -> LoginState.EmptyEmailOrPassword(
                    currentUser
                )
                !emailRegex.matches(currentUser.email) -> LoginState.WrongEmailError(currentUser)
                else -> {
                    val userExists = checkIfUserExistsByEmailUseCase.execute(
                        CheckIfUserExistsByEmailUseCase.Params(uiState.userUI.email)
                    )

                    Log.d("xxx", "userExists: $userExists")
                    when {
                        userExists -> {
                            val user =
                                getUserByEmailUseCase.execute(GetUserByEmailUseCase.Params(uiState.userUI.email))
                            if (user.password == currentUser.password) {
                                insertOwnUserEmailUseCase.execute(InsertOwnUserEmailUseCase.Params(currentUser.email))
                                LoginState.Success
                            } else {
                                LoginState.WrongPasswordError(currentUser)
                            }
                        }
                        else -> {
                            LoginState.UserNotExists(currentUser)
                        }
                    }
                }
            }
        }
    }


    companion object {
        val factory = viewModelFactory {
            initializer {
                LoginViewModel(
                    getUserByEmailUseCase = TempData.getUserByEmailUseCase,
                    checkIfUserExistsByEmailUseCase = TempData.checkIfUserExistsByEmailUseCase,
                    insertOwnUserEmailUseCase = TempData.insertOwnUserEmailUseCase
                )
            }
        }
    }
}