package com.example.coroutinescomposeapp.screen.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.rounded.*
import com.example.coroutinescomposeapp.R
import com.example.coroutinescomposeapp.model.CategoryUI
import com.example.coroutinescomposeapp.model.DescriptionItemUI

object TempData {

    val listOfIcons: List<CategoryUI> =
        listOf(
            CategoryUI(imageVector = Icons.Outlined.PhoneAndroid, description = "Phones"),
            CategoryUI(imageVector = Icons.Rounded.Headphones, description = "Headphones"),
            CategoryUI(imageVector = Icons.Rounded.SportsEsports, description = "Games"),
            CategoryUI(imageVector = Icons.Rounded.DirectionsCar, description = "Cars"),
            CategoryUI(imageVector = Icons.Rounded.NightShelter, description = "Furniture"),
            CategoryUI(imageVector = Icons.Rounded.SmartToy, description = "Kids"),
        )
    val descriptionItemUI =
        DescriptionItemUI(
            itemImages = listOf(
                R.drawable.ic_launcher_background,
                R.drawable.ic_apple,
                R.drawable.ic_google
            ), itemName = "New balance Sneakers",
            itemDescription = "Features waterproof, fire, air resistant shoes. all changed when the country of fire attacked",
            itemRating = 3.9,
            itemCountOfReviews = 4000,
            itemPrice = 0,
            colors = listOf(
                "#ffffff",
                "#b5b5b5",
                "#000000"
            )
        )
}