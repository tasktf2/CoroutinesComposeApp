package com.example.coroutinescomposeapp.domain.usecase

import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.repo.UserRepo

class InsertOwnUserEmailUseCase(private val userRepo: UserRepo) :
    UseCase<InsertOwnUserEmailUseCase.Params, Unit> {

    data class Params(val userEmail: String)

    override suspend fun execute(params: Params) = userRepo.insertOwnUserEmail(params.userEmail)

}