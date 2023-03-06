package com.example.coroutinescomposeapp.model

data class CardItemUI( //todo what + does
    val itemName: String,
    val itemPrice: Int,
    val categoryUI: CategoryUI,
    val isFavorite: Boolean = false
)
