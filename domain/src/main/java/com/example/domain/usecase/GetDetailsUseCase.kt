package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.model.Details
import com.example.domain.repo.DetailsRepo
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val detailsRepo: DetailsRepo) : UseCase<Unit, Details> {

    override suspend fun execute(params: Unit): Details = detailsRepo.getDetails()
}