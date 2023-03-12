package com.example.coroutinescomposeapp.domain.model

import com.example.coroutinescomposeapp.domain.base.DomainMapper
import com.example.coroutinescomposeapp.domain.base.Model
import com.example.coroutinescomposeapp.ui.model.DetailsUI

data class Details(
    val name: String,
    val description: String,
    val rating: Double,
    val numberOfReviews: Int,
    val price: Double,
    val colors: List<String>,
    val listOfImageUrls: List<String>
) : Model

class DetailsDomainMapper : DomainMapper<Details, DetailsUI> {
    override fun mapToUI(domain: Details): DetailsUI = DetailsUI(
        itemName = domain.name,
        itemDescription = domain.description,
        itemPrice = domain.price,
        itemRating = domain.rating,
        itemCountOfReviews = domain.numberOfReviews,
        colors = domain.colors,
        itemImages = domain.listOfImageUrls
    )

    override fun mapToDomain(item: DetailsUI): Details {
        TODO("Not yet implemented")
    }
}
