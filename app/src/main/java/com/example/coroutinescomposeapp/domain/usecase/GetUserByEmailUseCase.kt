package com.example.coroutinescomposeapp.domain.usecase

import com.example.coroutinescomposeapp.domain.base.DomainMapper
import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.model.User
import com.example.coroutinescomposeapp.domain.repo.UserRepo
import com.example.coroutinescomposeapp.ui.model.UserUI

class GetUserByEmailUseCase(
    private val userRepo: UserRepo,
    private val userDomainMapper: DomainMapper<User, UserUI>
) :
    UseCase<GetUserByEmailUseCase.Params, UserUI> {

    data class Params(val userMail: String)

    override suspend fun execute(params: Params): UserUI =
        userDomainMapper.mapToUI(userRepo.getUserByEmail(params.userMail))
}