package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.repo.UserRepo
import javax.inject.Inject

class DeleteUserByEmailUseCase @Inject constructor(private val userRepo: UserRepo) :
    UseCase<DeleteUserByEmailUseCase.Params, Unit> {

    data class Params(val userMail: String)

    override suspend fun execute(params: Params) = userRepo.deleteUserByEmail(params.userMail)
}