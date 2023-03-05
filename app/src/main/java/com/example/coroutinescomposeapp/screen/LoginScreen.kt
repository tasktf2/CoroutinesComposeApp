package com.example.coroutinescomposeapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coroutinescomposeapp.ui.theme.CoroutinesComposeAppTheme

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    CoroutinesComposeAppTheme {
        LoginScreen()
    }
}

@Composable
private fun LoginScreen() {
    var valueEmail by remember {
        mutableStateOf("")
    }
    var valuePassword by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(modifier = Modifier.fillMaxHeight(0.2f), verticalArrangement = Arrangement.Center) {
            HeaderText("Welcome back")
        }

        Column(
            modifier = Modifier.fillMaxHeight(0.45f), verticalArrangement = Arrangement.SpaceBetween
        ) {
            SignUpEditText("E-mail", value = valueEmail, onValueChange = { valueEmail = it })
            PasswordEditText(value = valuePassword, onValueChange = { valuePassword = it })
            ButtonWithText(text = "Login") {}
        }
    }
}

@Composable
fun HeaderText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h1
    )
}

@Composable
fun PasswordEditText(value: String, onValueChange: (String) -> Unit) {
    val passwordVisibility = remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(fraction = 0.8f)
            .clip(RoundedCornerShape(16.dp)),
        visualTransformation = if (passwordVisibility.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            val image = if (passwordVisibility.value) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }
            val description = when {
                passwordVisibility.value -> "Hide password"
                else -> "Show password"
            }
            IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                Icon(imageVector = image, contentDescription = description)
            }
        },
        placeholder = {
            Text(
                text = "Password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5
            )
        })
}