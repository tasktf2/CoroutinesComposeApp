package com.example.coroutinescomposeapp.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinescomposeapp.ui.model.UserUI
import com.example.coroutinescomposeapp.ui.model.toUi
import com.example.domain.base.UseCase
import com.example.domain.model.User
import com.example.domain.usecase.CheckIfUserExistsByEmailUseCase
import com.example.domain.usecase.GetUserByEmailUseCase
import com.example.domain.usecase.InsertOwnUserEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginState(open val userUI: UserUI) {
    object Initial : LoginState(UserUI())
    data class Active(override val userUI: UserUI) : LoginState(userUI)
    data class WrongEmailError(override val userUI: UserUI) : LoginState(userUI)
    data class WrongPasswordError(override val userUI: UserUI) : LoginState(userUI)
    data class EmptyEmailOrPassword(override val userUI: UserUI) : LoginState(userUI)
    data class UserNotExists(override val userUI: UserUI) : LoginState(userUI)
    object Success : LoginState(UserUI())
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserByEmailUseCase: @JvmSuppressWildcards UseCase<GetUserByEmailUseCase.Params, User>,
    private val checkIfUserExistsByEmailUseCase: @JvmSuppressWildcards UseCase<CheckIfUserExistsByEmailUseCase.Params, Boolean>,
    private val insertOwnUserEmailUseCase: @JvmSuppressWildcards UseCase<InsertOwnUserEmailUseCase.Params, Unit>
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
                    when {
                        userExists -> {
                            val user =
                                getUserByEmailUseCase.execute(
                                    GetUserByEmailUseCase.Params(
                                        uiState.userUI.email
                                    )
                                ).toUi()
                            if (user.password == currentUser.password) {
                                insertOwnUserEmailUseCase.execute(
                                    InsertOwnUserEmailUseCase.Params(
                                        currentUser.email
                                    )
                                )
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
}