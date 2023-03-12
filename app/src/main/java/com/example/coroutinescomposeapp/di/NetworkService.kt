package com.example.coroutinescomposeapp.di

import com.example.coroutinescomposeapp.data.remote.api.DetailsApi
import com.example.coroutinescomposeapp.data.remote.api.ProductApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkService {

    private const val BASE_URL = "https://run.mocky.io/v3/"

    private val interceptor: Interceptor by lazy {
        Interceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }
    }

    private val networkInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor(networkInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val productApi: ProductApi by lazy { retrofit.create(ProductApi::class.java) }
    val detailsApi: DetailsApi by lazy { retrofit.create(DetailsApi::class.java) }
}