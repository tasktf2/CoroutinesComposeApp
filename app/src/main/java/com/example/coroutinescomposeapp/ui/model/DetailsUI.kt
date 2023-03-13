package com.example.coroutinescomposeapp.ui.model

import com.example.coroutinescomposeapp.ui.base.Item
import com.example.domain.model.Details

data class DetailsUI(
    val itemName: String,
    val itemDescription: String,
    val itemPrice: Double,
    val itemRating: Double,
    val itemCountOfReviews: Int,
    val colors: List<String>,
    val itemImages: List<String>,
    val isFavorite: Boolean = false
) : Item

fun Details.toUi(): DetailsUI = DetailsUI(
    itemName = name,
    itemDescription = description,
    itemPrice = price,
    itemRating = rating,
    itemCountOfReviews = numberOfReviews,
    colors = colors,
    itemImages = listOfImageUrls
)