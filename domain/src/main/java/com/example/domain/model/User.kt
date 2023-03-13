package com.example.domain.model

import com.example.domain.base.Model

data class User(
    val email: String,
    val firstName: String,
    val secondName: String,
    val password: String
) : Model
