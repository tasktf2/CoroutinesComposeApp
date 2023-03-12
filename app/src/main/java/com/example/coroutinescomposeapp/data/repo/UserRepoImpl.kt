package com.example.coroutinescomposeapp.data.repo

import android.content.SharedPreferences
import com.example.coroutinescomposeapp.data.base.EntityMapper
import com.example.coroutinescomposeapp.data.local.db.dao.UserDao
import com.example.coroutinescomposeapp.data.local.model.UserEntity
import com.example.coroutinescomposeapp.domain.model.User
import com.example.coroutinescomposeapp.domain.repo.UserRepo

class UserRepoImpl(
    private val userDao: UserDao,
    private val userEntityMapper: EntityMapper<UserEntity, User>,
    private val sharedPreferences: SharedPreferences
) :
    UserRepo {

    override suspend fun insertNewUser(user: User) {
        insertOwnUserEmail(user.email)
        userDao.insertNewUser(userEntityMapper.mapToEntity(user))
    }

    override suspend fun getUserByEmail(userMail: String): User =
        userEntityMapper.mapToDomain(userDao.getUserByEmail(userMail))

    override suspend fun checkIfUserExistsByEmail(userMail: String): Boolean =
        userDao.checkIfUserExistsByEmail(userMail)

    override suspend fun deleteUserByEmail(userMail: String) = userDao.deleteUserByEmail(userMail)

    override fun getOwnUserEmail(): String =
        sharedPreferences.getString(KEY_OWN_USER_EMAIL, DEFAULT_USER_EMAIL).orEmpty()

    override fun insertOwnUserEmail(userEmail: String) =
        sharedPreferences.edit().putString(KEY_OWN_USER_EMAIL, userEmail).apply()

    companion object {

        private const val KEY_OWN_USER_EMAIL = "KEY_OWN_USER_EMAIL"

        const val DEFAULT_USER_EMAIL = "example@gmail.com"
    }
}