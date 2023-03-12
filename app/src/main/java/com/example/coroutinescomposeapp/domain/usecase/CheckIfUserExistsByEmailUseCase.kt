package com.example.coroutinescomposeapp.domain.usecase

import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.repo.UserRepo

class CheckIfUserExistsByEmailUseCase(private val userRepo: UserRepo) :
    UseCase<CheckIfUserExistsByEmailUseCase.Params, Boolean> {

    data class Params(val userMail: String)

    override suspend fun execute(params: Params): Boolean =
        userRepo.checkIfUserExistsByEmail(params.userMail)
}