package com.example.domain.usecase

import com.example.domain.base.UseCase
import com.example.domain.model.Product
import com.example.domain.repo.ProductRepo
import javax.inject.Inject

class GetFlashSaleProductsUseCase @Inject constructor(private val productRepo: ProductRepo) :
    UseCase<Unit, List<Product>> {

    override suspend fun execute(params: Unit): List<Product> = productRepo.getFlashSaleItems()
}