package com.example.coroutinescomposeapp.domain.usecase

import com.example.coroutinescomposeapp.domain.base.DomainMapper
import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.model.User
import com.example.coroutinescomposeapp.domain.repo.UserRepo
import com.example.coroutinescomposeapp.ui.model.UserUI

class InsertUserUseCase(
    private val userRepo: UserRepo,
    private val userDomainMapper: DomainMapper<User, UserUI>
) :
    UseCase<InsertUserUseCase.Params, Unit> {

    data class Params(val userUI: UserUI)

    override suspend fun execute(params: Params) = userRepo.insertNewUser(
        userDomainMapper.mapToDomain(params.userUI)
    )
}