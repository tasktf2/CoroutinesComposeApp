package com.example.coroutinescomposeapp.screen.base

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class CardType(
    val groupName: String,
    val width: Dp,
    val height: Dp,
    val categoryPaddingVertical: Dp,
    val categoryPaddingHorizontal: Dp,
    val categoryFontSize: Int,
    val nameFontSize: Int,
    val priceFontSize: Int,
) {
    LATEST(
        groupName = "Latest",
        width = 114.dp,
        height = 150.dp,
        categoryPaddingVertical = 4.dp,
        categoryPaddingHorizontal = 7.dp,
        categoryFontSize = 6,
        nameFontSize = 9,
        priceFontSize = 7
    ),
    FLASH_SALE(
        groupName = "Flash Sale",
        width = 174.dp,
        height = 220.dp,
        categoryPaddingVertical = 6.dp,
        categoryPaddingHorizontal = 17.dp,
        categoryFontSize = 9,
        nameFontSize = 13,
        priceFontSize = 10
    ),
    BRANDS(
        groupName = "Brands",
        width = 114.dp,
        height = 150.dp,
        categoryPaddingVertical = 4.dp,
        categoryPaddingHorizontal = 7.dp,
        categoryFontSize = 6,
        nameFontSize = 9,
        priceFontSize = 7
    )
}
