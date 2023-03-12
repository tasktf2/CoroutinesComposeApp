package com.example.coroutinescomposeapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coroutinescomposeapp.R
import com.example.coroutinescomposeapp.ui.theme.BrightRed
import com.example.coroutinescomposeapp.ui.theme.CoroutinesComposeAppTheme
import com.example.coroutinescomposeapp.ui.theme.VeryLightGray

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    CoroutinesComposeAppTheme {
//        LoginScreen {}
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel, onLoginClicked: () -> Unit) {

    val uiState = viewModel.uiState

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    DisposableEffect(uiState) {
        when (uiState) {
            is LoginState.Success -> onLoginClicked()
            else -> {}
        }
        onDispose {}
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(modifier = Modifier.fillMaxHeight(0.2f), verticalArrangement = Arrangement.Center) {
            HeaderText(stringResource(id = R.string.login_welcome_back))
        }

        Column(
            modifier = Modifier.fillMaxHeight(0.45f), verticalArrangement = Arrangement.SpaceBetween
        ) {
            CustomTextField(
                placeholder = stringResource(R.string.e_mail),
                value = uiState.userUI.email,
                onValueChange = viewModel::emailChanged,
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f)
                    .height(29.dp)
                    .background(color = VeryLightGray, shape = MaterialTheme.shapes.large)
            )
            CustomTextField(
                placeholder = stringResource(R.string.password),
                value = uiState.userUI.password,
                onValueChange = viewModel::passwordChanged,
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f)
                    .height(29.dp)
                    .background(color = VeryLightGray, shape = MaterialTheme.shapes.large),
                trailingIcon = {
                    val image = if (passwordVisibility) {
                        Icons.Outlined.Visibility
                    } else {
                        Icons.Outlined.VisibilityOff
                    }
                    val description = when {
                        passwordVisibility -> stringResource(R.string.hide_password)
                        else -> stringResource(R.string.show_password)
                    }
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                },
                visualTransformation = if (passwordVisibility) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            )
            if (uiState is LoginState.EmptyEmailOrPassword) {
                Text(
                    text = stringResource(R.string.error_empty_fields),
                    style = MaterialTheme.typography.h5,
                    color = BrightRed,
                )
            }
            if (uiState is LoginState.WrongEmailError) {
                Text(
                    text = stringResource(R.string.error_email_type),
                    style = MaterialTheme.typography.h5,
                    color = BrightRed,
                )
            }
            if (uiState is LoginState.WrongPasswordError) {
                Text(
                    text = stringResource(R.string.error_password),
                    style = MaterialTheme.typography.h5,
                    color = BrightRed,
                )
            }
            if (uiState is LoginState.UserNotExists) {
                Text(
                    text = stringResource(R.string.error_email_not_registered),
                    style = MaterialTheme.typography.h5,
                    color = BrightRed,
                )
            }
            ButtonWithText(
                text = stringResource(R.string.login),
                onClick = viewModel::loginButtonClicked
            )
        }
    }
}

@Composable
internal fun HeaderText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h1
    )
}