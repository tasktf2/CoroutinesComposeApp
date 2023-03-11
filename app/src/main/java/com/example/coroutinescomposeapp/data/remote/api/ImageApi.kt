package com.example.coroutinescomposeapp.data.remote.api

import com.example.coroutinescomposeapp.data.remote.response.ProductsResponse
import retrofit2.http.GET

interface ImageApi {
    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestItems(): ProductsResponse

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSaleItems(): ProductsResponse
}