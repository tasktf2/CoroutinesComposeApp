package com.example.coroutinescomposeapp.domain.usecase

import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.repo.UserRepo

class DeleteUserByEmailUseCase(private val userRepo: UserRepo) :
    UseCase<DeleteUserByEmailUseCase.Params, Unit> {

    data class Params(val userMail: String)

    override suspend fun execute(params: Params) = userRepo.deleteUserByEmail(params.userMail)
}