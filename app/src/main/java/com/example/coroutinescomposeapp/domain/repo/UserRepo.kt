package com.example.coroutinescomposeapp.domain.repo

import com.example.coroutinescomposeapp.data.local.model.UserEntity
import com.example.coroutinescomposeapp.domain.model.User

interface UserRepo {

    fun insertNewUser(user: UserEntity)

    fun getUserByEmail(userMail: String): User

    fun deleteUserByEmail(userMail: String)
}