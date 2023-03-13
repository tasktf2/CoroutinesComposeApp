package com.example.coroutinescomposeapp.ui.model

import com.example.coroutinescomposeapp.ui.base.Item
import com.example.domain.model.User

data class UserUI(
    val email: String = "",
    val firstName: String = "",
    val secondName: String = "",
    val password: String = ""
) : Item

fun User.toUi() = UserUI(
    email = email,
    firstName = firstName,
    secondName = secondName,
    password = password
)

fun UserUI.toDomain() = User(
    email = email,
    firstName = firstName,
    secondName = secondName,
    password = password
)