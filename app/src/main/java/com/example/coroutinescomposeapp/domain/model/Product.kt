package com.example.coroutinescomposeapp.domain.model

import com.example.coroutinescomposeapp.domain.base.DomainMapper
import com.example.coroutinescomposeapp.domain.base.Model
import com.example.coroutinescomposeapp.ui.model.CardUI

data class Product(
    val category: String,
    val name: String,
    val price: Double,
    val discount: Int?,
    val imageUrl: String
) : Model

class ProductDomainMapper : DomainMapper<Product, CardUI> {

    override fun mapToUI(domain: Product): CardUI = CardUI(
        itemImageUrl = domain.imageUrl,
        itemName = domain.name,
        itemPrice = domain.price,
        itemCategory = domain.category,
        itemDiscount = domain.discount
    )

    override fun mapToDomain(item: CardUI): Product {
        TODO("Not yet implemented")
    }
}
