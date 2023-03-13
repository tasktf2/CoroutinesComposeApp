package com.example.domain.repo

import com.example.domain.model.Product

interface ProductRepo {
    suspend fun getLatestItems(): List<Product>

    suspend fun getFlashSaleItems(): List<Product>
}