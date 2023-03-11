package com.example.coroutinescomposeapp.ui.model

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
    val startPadding: Dp,
    val categoryTopPadding: Dp,
    val itemNameTopPadding: Dp,
    val priceBottomPadding: Dp,
) {
    LATEST(
        groupName = "Latest",
        width = 114.dp,
        height = 150.dp,
        categoryPaddingVertical = 4.dp,
        categoryPaddingHorizontal = 7.dp,
        categoryFontSize = 6,
        nameFontSize = 9,
        priceFontSize = 7,
        startPadding = 7.dp,
        categoryTopPadding = 91.dp,
        itemNameTopPadding = 106.dp,
        priceBottomPadding = (-7).dp
    ),
    FLASH_SALE(
        groupName = "Flash Sale",
        width = 174.dp,
        height = 220.dp,
        categoryPaddingVertical = 6.dp,
        categoryPaddingHorizontal = 17.dp,
        categoryFontSize = 9,
        nameFontSize = 13,
        priceFontSize = 10,
        startPadding = 9.dp,
        categoryTopPadding = 121.dp,
        itemNameTopPadding = 144.dp,
        priceBottomPadding = (-17).dp
    ),
    BRANDS(
        groupName = "Brands",
        width = 114.dp,
        height = 150.dp,
        categoryPaddingVertical = 4.dp,
        categoryPaddingHorizontal = 7.dp,
        categoryFontSize = 6,
        nameFontSize = 9,
        priceFontSize = 7,
        startPadding = 7.dp,
        categoryTopPadding = 91.dp,
        itemNameTopPadding = 106.dp,
        priceBottomPadding = (-7).dp
    )
}
