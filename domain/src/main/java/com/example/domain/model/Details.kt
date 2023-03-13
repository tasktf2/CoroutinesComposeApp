package com.example.domain.model

import com.example.domain.base.Model

data class Details(
    val name: String,
    val description: String,
    val rating: Double,
    val numberOfReviews: Int,
    val price: Double,
    val colors: List<String>,
    val listOfImageUrls: List<String>
) : Model