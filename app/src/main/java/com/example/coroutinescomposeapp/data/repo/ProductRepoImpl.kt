package com.example.coroutinescomposeapp.data.repo

import com.example.coroutinescomposeapp.data.base.RemoteMapper
import com.example.coroutinescomposeapp.data.remote.api.ProductApi
import com.example.coroutinescomposeapp.data.remote.response.ProductRemote
import com.example.coroutinescomposeapp.domain.model.Product
import com.example.coroutinescomposeapp.domain.repo.ProductRepo

class ProductRepoImpl(
    private val api: ProductApi,
    private val productEntityMapper: RemoteMapper<ProductRemote, Product>,
) : ProductRepo {
    override suspend fun getLatestItems(): List<Product> =
        api.getLatestItems().products.map(productEntityMapper::mapToDomain)

    override suspend fun getFlashSaleItems(): List<Product> =
        api.getFlashSaleItems().products.map(productEntityMapper::mapToDomain)
}