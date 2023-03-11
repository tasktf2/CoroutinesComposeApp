package com.example.coroutinescomposeapp.ui.model

data class CardItemUI(
    val itemImageUrl: String,
    val itemName: String,
    val itemPrice: Double,
    val itemCategory: String,
    val itemDiscount: Int? = null,
    val isFavorite: Boolean = false
)