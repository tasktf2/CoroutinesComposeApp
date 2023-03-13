package com.example.coroutinescomposeapp.ui.model

import com.example.coroutinescomposeapp.ui.base.Item
import com.example.domain.model.Product

data class CardUI(
    val itemImageUrl: String,
    val itemName: String,
    val itemPrice: Double,
    val itemCategory: String,
    val itemDiscount: Int? = null,
    val isFavorite: Boolean = false
) : Item

fun Product.toUi() = CardUI(
    itemImageUrl = imageUrl,
    itemName = name,
    itemPrice = price,
    itemCategory = category,
    itemDiscount = discount
)