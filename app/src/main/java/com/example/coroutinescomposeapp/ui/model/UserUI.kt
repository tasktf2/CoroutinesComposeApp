package com.example.coroutinescomposeapp.ui.model

import com.example.coroutinescomposeapp.ui.base.Item

data class UserUI(
    val email: String,
    val firstName: String,
    val secondName: String,
    val password: String
) : Item