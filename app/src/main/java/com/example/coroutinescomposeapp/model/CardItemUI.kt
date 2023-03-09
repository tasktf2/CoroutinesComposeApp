package com.example.coroutinescomposeapp.model

import androidx.annotation.DrawableRes

data class CardItemUI( //todo what + does
    @DrawableRes val itemImage: Int,
    val itemName: String,
    val itemPrice: Int,
    val itemCategory: String,
    val isFavorite: Boolean = false
)