package com.example.coroutinescomposeapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SimpleScreen(screenName: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = screenName, modifier = Modifier.align(Alignment.Center))
    }
}