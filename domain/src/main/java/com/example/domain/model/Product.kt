package com.example.domain.model

import com.example.domain.base.Model

data class Product(
    val category: String,
    val name: String,
    val price: Double,
    val discount: Int?,
    val imageUrl: String
) : Model

