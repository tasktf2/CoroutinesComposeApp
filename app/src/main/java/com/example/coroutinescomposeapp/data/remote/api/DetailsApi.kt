package com.example.coroutinescomposeapp.data.remote.api

import com.example.coroutinescomposeapp.data.remote.response.DetailsResponse
import retrofit2.http.GET

interface DetailsApi {
    @GET("f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getDetails(): DetailsResponse
}