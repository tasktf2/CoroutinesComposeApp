package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.model.User
import com.example.domain.repo.UserRepo
import javax.inject.Inject

class GetUserByEmailUseCase @Inject constructor(private val userRepo: UserRepo) :
    UseCase<GetUserByEmailUseCase.Params, User> {

    data class Params(val userMail: String)

    override suspend fun execute(params: Params): User = userRepo.getUserByEmail(params.userMail)
}