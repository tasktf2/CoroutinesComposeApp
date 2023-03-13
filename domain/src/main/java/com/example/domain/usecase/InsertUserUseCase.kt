package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.model.User
import com.example.domain.repo.UserRepo
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val userRepo: UserRepo) :
    UseCase<InsertUserUseCase.Params, Unit> {

    data class Params(val user: User)

    override suspend fun execute(params: Params) = userRepo.insertNewUser(params.user)
}