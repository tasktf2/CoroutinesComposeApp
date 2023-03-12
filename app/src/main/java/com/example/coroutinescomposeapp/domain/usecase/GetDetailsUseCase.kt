package com.example.coroutinescomposeapp.domain.usecase

import com.example.coroutinescomposeapp.domain.base.DomainMapper
import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.model.Details
import com.example.coroutinescomposeapp.domain.repo.DetailsRepo
import com.example.coroutinescomposeapp.ui.model.DetailsUI

class GetDetailsUseCase(
    private val detailsDomainMapper: DomainMapper<Details, DetailsUI>,
    private val detailsRepo: DetailsRepo
) : UseCase<Unit, DetailsUI> {

    override suspend fun execute(params: Unit): DetailsUI =
        detailsDomainMapper.mapToUI(detailsRepo.getDetails())
}