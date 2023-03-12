package com.example.coroutinescomposeapp.domain.repo

import com.example.coroutinescomposeapp.domain.model.Product

interface ProductRepo {
    suspend fun getLatestItems(): List<Product>

    suspend fun getFlashSaleItems(): List<Product>
}