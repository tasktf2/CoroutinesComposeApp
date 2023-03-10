package com.example.domain.repo

import com.example.domain.model.User

interface UserRepo {

    suspend fun insertNewUser(user: User)

    suspend fun getUserByEmail(userMail: String): User

    suspend fun checkIfUserExistsByEmail(userMail: String): Boolean

    suspend fun deleteUserByEmail(userMail: String)

    fun getOwnUserEmail(): String

    fun insertOwnUserEmail(userEmail: String)
}