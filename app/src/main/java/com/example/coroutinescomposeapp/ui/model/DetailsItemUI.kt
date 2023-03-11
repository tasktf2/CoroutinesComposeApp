package com.example.coroutinescomposeapp.ui.model

data class DetailsItemUI(
    val itemName: String,
    val itemDescription: String,
    val itemPrice: Double,
    val itemRating: Double,
    val itemCountOfReviews: Int,
    val colors: List<String>,
    val itemImages: List<String>,
    val isFavorite: Boolean = false
)