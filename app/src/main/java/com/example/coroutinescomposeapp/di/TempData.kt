package com.example.coroutinescomposeapp.di

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.material.icons.rounded.*
import com.example.coroutinescomposeapp.data.remote.response.DetailsEntityMapper
import com.example.coroutinescomposeapp.data.remote.response.ProductEntityMapper
import com.example.coroutinescomposeapp.data.repo.DetailsRepoImpl
import com.example.coroutinescomposeapp.data.repo.ProductRepoImpl
import com.example.coroutinescomposeapp.domain.base.UseCase
import com.example.coroutinescomposeapp.domain.model.DetailsDomainMapper
import com.example.coroutinescomposeapp.domain.model.ProductDomainMapper
import com.example.coroutinescomposeapp.domain.repo.ProductRepo
import com.example.coroutinescomposeapp.domain.usecase.GetDetailsUseCase
import com.example.coroutinescomposeapp.domain.usecase.GetFlashSaleProductsUseCase
import com.example.coroutinescomposeapp.domain.usecase.GetLatestProductsUseCase
import com.example.coroutinescomposeapp.ui.model.CardUI
import com.example.coroutinescomposeapp.ui.model.CategoryUI
import com.example.coroutinescomposeapp.ui.model.DetailsUI

object TempData {

    private val productEntityMapper by lazy { ProductEntityMapper() }
    private val detailsEntityMapper by lazy { DetailsEntityMapper() }

    private val productRepo: ProductRepo by lazy {
        ProductRepoImpl(
            NetworkService.productApi,
            productEntityMapper
        )
    }
    private val detailsRepo by lazy {
        DetailsRepoImpl(
            NetworkService.detailsApi,
            detailsEntityMapper
        )
    }

    private val productDomainMapper by lazy { ProductDomainMapper() }
    private val detailsDomainMapper by lazy { DetailsDomainMapper() }

    val getLatestProductsUseCase: UseCase<Unit, List<CardUI>> by lazy {
        GetLatestProductsUseCase(
            productRepo = productRepo,
            productDomainMapper = productDomainMapper
        )
    }
    val getFlashSaleProductsUseCase: UseCase<Unit, List<CardUI>> by lazy {
        GetFlashSaleProductsUseCase(
            productRepo = productRepo,
            productDomainMapper = productDomainMapper
        )
    }
    val GetDetailsUseCase: UseCase<Unit, DetailsUI> by lazy {
        GetDetailsUseCase(
            detailsDomainMapper,
            detailsRepo
        )
    }

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