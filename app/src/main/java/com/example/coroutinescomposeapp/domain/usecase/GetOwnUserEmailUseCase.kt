package com.example.coroutinescomposeapp.domain.usecase

import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.repo.UserRepo

class GetOwnUserEmailUseCase(private val userRepo: UserRepo) : UseCase<Unit, String> {

    override suspend fun execute(params: Unit): String = userRepo.getOwnUserEmail()
}