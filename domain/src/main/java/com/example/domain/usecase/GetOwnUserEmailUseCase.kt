package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.repo.UserRepo
import javax.inject.Inject

class GetOwnUserEmailUseCase @Inject constructor(private val userRepo: UserRepo) :
    UseCase<Unit, String> {

    override suspend fun execute(params: Unit): String = userRepo.getOwnUserEmail()
}