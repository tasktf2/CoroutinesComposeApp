package com.example.coroutinescomposeapp.data.remote.response

import com.google.gson.annotations.SerializedName

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
)

data class DetailsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("rating") val rating: Double,
    @SerializedName("number_of_reviews") val numberOfReviews: Int,
    @SerializedName("price") val price: Double,
    @SerializedName("colors") val colors: List<String>,
    @SerializedName("image_urls") val listOfImageUrls: List<String>
)