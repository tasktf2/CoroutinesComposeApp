package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.repo.UserRepo
import javax.inject.Inject

class CheckIfUserExistsByEmailUseCase @Inject constructor(private val userRepo: UserRepo) :
    UseCase<CheckIfUserExistsByEmailUseCase.Params, Boolean> {

    data class Params(val userMail: String)

    override suspend fun execute(params: Params): Boolean =
        userRepo.checkIfUserExistsByEmail(params.userMail)
}