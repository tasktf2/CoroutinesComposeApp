package com.example.coroutinescomposeapp.domain.usecase

import com.example.coroutinescomposeapp.domain.base.DomainMapper
import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.model.Product
import com.example.coroutinescomposeapp.domain.repo.ProductRepo
import com.example.coroutinescomposeapp.ui.model.CardUI

class GetLatestProductsUseCase(
    private val productRepo: ProductRepo,
    private val productDomainMapper: DomainMapper<Product, CardUI>
) : UseCase<Unit, List<CardUI>> {

    override suspend fun execute(params: Unit): List<CardUI> =
        productRepo.getLatestItems().map(productDomainMapper::mapToUI)
}