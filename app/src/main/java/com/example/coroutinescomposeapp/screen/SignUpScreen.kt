package com.example.coroutinescomposeapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coroutinescomposeapp.R
import com.example.coroutinescomposeapp.montserratFontFamily
import com.example.coroutinescomposeapp.ui.theme.CoroutinesComposeAppTheme

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    CoroutinesComposeAppTheme {
        SignUpScreen()
    }
}

@Composable
private fun SignUpScreen() {

    var valueFirstName by remember { mutableStateOf("") }
    var valueSecondName by remember { mutableStateOf("") }
    var valueEmail by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight(0.25f)) {
            HeaderText("Sign Up")
        }
        Column(
            modifier = Modifier.fillMaxHeight(0.6f), verticalArrangement = Arrangement.SpaceBetween
        ) {
            SignUpEditText(
                placeholder = "First name",
                value = valueFirstName,
                onValueChange = { valueFirstName = it })
            SignUpEditText(
                placeholder = "Second name",
                value = valueSecondName,
                onValueChange = { valueSecondName = it })
            SignUpEditText(
                placeholder = "First name",
                value = valueEmail,
                onValueChange = { valueEmail = it })
            ButtonWithText(text = "Sign up")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.3f)
                .padding(top = 10.dp)
        ) {
            SignInText {}
        }
        Column(
            modifier = Modifier.fillMaxHeight(0.5f), verticalArrangement = Arrangement.Center
        ) {
            SignUpWithAnotherResource(
                imageRes = R.drawable.ic_google, resource = "Google"
            ) {}
            SignUpWithAnotherResource(
                imageRes = R.drawable.ic_apple, resource = "Apple"
            ) {}
        }
    }
}

@Composable
fun SignUpEditText(placeholder: String, value: String, onValueChange: (String) -> Unit) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(fraction = 0.8f)
            .clip(RoundedCornerShape(16.dp)),
        placeholder = {
            Text(
                text = placeholder,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = montserratFontFamily,
                fontWeight = FontWeight.Medium
            )
        })
}

@Composable
private fun SignInText(onClick: () -> Unit) {
    Text(
        text = "Already have an account? ",
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium
    )
    Text(
        text = "Sign in",
        modifier = Modifier.clickable(onClick = onClick),
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Medium,
        color = Color.Blue
    )
}

@Composable
fun SignUpWithAnotherResource(imageRes: Int, resource: String, onClick: () -> Unit) {
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
            fontFamily = montserratFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
    }
}