package com.example.data.remote.response

import com.example.data.base.RemoteMapper
import com.example.domain.model.Details
import com.example.domain.model.Product
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ProductsResponse(
    @SerializedName(value = "latest", alternate = ["flash_sale"])
    val products: List<ProductRemote>
)

data class ProductRemote(
    @SerializedName("category") val category: String,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double,
    @SerializedName("discount") val discount: Int?,
    @SerializedName("image_url") val imageUrl: String
) : com.example.data.base.ModelRemote

data class DetailsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("number_of_reviews") val numberOfReviews: Int,
    @SerializedName("price") val price: Double,
    @SerializedName("colors") val colors: List<String>,
    @SerializedName("image_urls") val listOfImageUrls: List<String>
) : com.example.data.base.ModelRemote

class ProductRemoteMapper @Inject constructor() : RemoteMapper<ProductRemote, Product> {
    override fun mapToDomain(remote: ProductRemote): Product =
        Product(
            category = remote.category,
            name = remote.name,
            price = remote.price,
            discount = remote.discount,
            imageUrl = remote.imageUrl
        )
}

class DetailsRemoteMapper @Inject constructor() : RemoteMapper<DetailsResponse, Details> {
    override fun mapToDomain(remote: DetailsResponse): Details = Details(
        name = remote.name,
        description = remote.description,
        rating = remote.rating,
        numberOfReviews = remote.numberOfReviews,
        price = remote.price,
        colors = remote.colors,
        listOfImageUrls = remote.listOfImageUrls
    )
}