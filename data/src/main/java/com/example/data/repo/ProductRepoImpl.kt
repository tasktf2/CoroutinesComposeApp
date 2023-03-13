package com.example.data.repo

import com.example.data.base.RemoteMapper
import com.example.data.remote.api.ProductApi
import com.example.data.remote.response.ProductRemote
import com.example.domain.model.Product
import com.example.domain.repo.ProductRepo

class ProductRepoImpl(
    private val api: ProductApi,
    private val productEntityMapper: RemoteMapper<ProductRemote, Product>,
) : ProductRepo {
    override suspend fun getLatestItems(): List<Product> =
        api.getLatestItems().products.map(productEntityMapper::mapToDomain)

    override suspend fun getFlashSaleItems(): List<Product> =
        api.getFlashSaleItems().products.map(productEntityMapper::mapToDomain)
}