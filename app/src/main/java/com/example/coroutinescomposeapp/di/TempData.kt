package com.example.coroutinescomposeapp.di

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.rounded.*
import com.example.coroutinescomposeapp.ui.model.CategoryUI

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
}