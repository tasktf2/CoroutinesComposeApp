package com.example.coroutinescomposeapp.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coroutinescomposeapp.R
import com.example.coroutinescomposeapp.ui.theme.CoroutinesComposeAppTheme
import com.example.coroutinescomposeapp.ui.theme.VeryLightGray

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    CoroutinesComposeAppTheme {
        SignUpScreen({}, {})
    }
}

@Composable
fun SignUpScreen(onSignUpClicked: () -> Unit, onLoginClicked: () -> Unit) {

    var valueFirstName by remember { mutableStateOf("") }
    var valueSecondName by remember { mutableStateOf("") }
    var valueEmail by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Column(modifier = Modifier.weight(0.2f), verticalArrangement = Arrangement.Center) {
            HeaderText(stringResource(id = R.string.sign_up))
        }
        Column(
            modifier = Modifier.weight(0.5f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                placeholder = stringResource(R.string.first_name),
                value = valueFirstName,
                onValueChange = { valueFirstName = it },
                modifier = Modifier
                    .height(29.dp)
                    .background(color = VeryLightGray, shape = MaterialTheme.shapes.large),
            )
            CustomTextField(
                placeholder = stringResource(R.string.second_name),
                value = valueSecondName,
                onValueChange = { valueSecondName = it },
                modifier = Modifier
                    .height(29.dp)
                    .background(color = VeryLightGray, shape = MaterialTheme.shapes.large),
            )
            CustomTextField(
                placeholder = stringResource(id = R.string.e_mail),
                value = valueEmail,
                onValueChange = { valueEmail = it },
                modifier = Modifier
                    .height(29.dp)
                    .background(color = VeryLightGray, shape = MaterialTheme.shapes.large),
            )
            ButtonWithText(text = stringResource(id = R.string.sign_up), onClick = onSignUpClicked)
        }

        SignInText(modifier = Modifier.weight(0.05f), onClick = onLoginClicked)

        Column(
            modifier = Modifier.weight(0.25f), verticalArrangement = Arrangement.Top
        ) {
            SignUpWithAnotherResource(
                imageRes = R.drawable.ic_google, resource = stringResource(R.string.google)
            ) {}
            SignUpWithAnotherResource(
                imageRes = R.drawable.ic_apple, resource = stringResource(R.string.apple)
            ) {}
        }
    }
}

@Composable
internal fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholder: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    BasicTextField(
        modifier = modifier
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = TextFieldDefaults.MinHeight
            ),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        cursorBrush = SolidColor(MaterialTheme.colors.primary),
        textStyle = MaterialTheme.typography.h5.copy(
            color = MaterialTheme.colors.onSurface
        ),
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            Box {
                if (leadingIcon != null) {
                    Box(modifier = Modifier.align(Alignment.CenterStart)) {
                        leadingIcon()
                    }
                }
                Box(
                    modifier = Modifier.align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                    innerTextField()
                }
                if (trailingIcon != null) {
                    Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                        trailingIcon()
                    }
                }
            }
        }
    )
}

@Composable
internal fun ButtonWithText(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(46.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Text(text = text)
    }
}

@Composable
private fun SignInText(modifier: Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .padding(top = 10.dp)
    ) {
        Text(
            text = buildString {
                append(stringResource(R.string.already_have_an_account))
                append(" ")
            },
            style = MaterialTheme.typography.h6
        )
        Text(
            text = stringResource(R.string.sign_in),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.clickable(onClick = onClick),
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun SignUpWithAnotherResource(
    @DrawableRes imageRes: Int,
    resource: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = resource,
            modifier = Modifier.size(width = 24.dp, height = 24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Sign up with $resource",
            style = MaterialTheme.typography.h4
        )
    }
}