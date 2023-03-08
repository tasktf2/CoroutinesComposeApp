package com.example.coroutinescomposeapp.model

import androidx.annotation.DrawableRes

data class DescriptionItemUI(
    @DrawableRes val itemImages: List<Int>,
    val itemName: String,
    val itemDescription: String,
    val itemPrice: Int,
    val itemRating: Double,
    val itemCountOfReviews: Int,
    val colors: List<String>,
    val isFavorite: Boolean = false
)