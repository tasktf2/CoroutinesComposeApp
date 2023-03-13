package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.repo.UserRepo
import javax.inject.Inject

class InsertOwnUserEmailUseCase @Inject constructor(private val userRepo: UserRepo) :
    UseCase<InsertOwnUserEmailUseCase.Params, Unit> {

    data class Params(val userEmail: String)

    override suspend fun execute(params: Params) = userRepo.insertOwnUserEmail(params.userEmail)

}