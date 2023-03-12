package com.example.coroutinescomposeapp.data.repo

import com.example.coroutinescomposeapp.data.base.EntityMapper
import com.example.coroutinescomposeapp.data.local.db.dao.UserDao
import com.example.coroutinescomposeapp.data.local.model.UserEntity
import com.example.coroutinescomposeapp.domain.model.User
import com.example.coroutinescomposeapp.domain.repo.UserRepo

class UserRepoImpl(
    private val userDao: UserDao,
    private val userEntityMapper: EntityMapper<UserEntity, User>
) :
    UserRepo {

    override fun insertNewUser(user: UserEntity) = userDao.insertNewUser(user)

    override fun getUserByEmail(userMail: String): User =
        userEntityMapper.mapToDomain(userDao.getUserByEmail(userMail))

    override fun deleteUserByEmail(userMail: String) = userDao.deleteUserByEmail(userMail)
}