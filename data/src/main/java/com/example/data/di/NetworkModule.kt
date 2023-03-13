package com.example.data.di

import com.example.data.base.RemoteMapper
import com.example.data.remote.api.DetailsApi
import com.example.data.remote.api.ProductApi
import com.example.data.remote.response.DetailsRemoteMapper
import com.example.data.remote.response.DetailsResponse
import com.example.data.remote.response.ProductRemote
import com.example.data.remote.response.ProductRemoteMapper
import com.example.domain.model.Details
import com.example.domain.model.Product
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object ProvideNetworkModule {
    private const val BASE_URL = "https://run.mocky.io/v3/"
    private const val CONNECT_TIMEOUT = 10L
    private const val READ_TIMEOUT = 10L

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideOkHttpClient(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addNetworkInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    fun provideInterceptor(): Interceptor =
        Interceptor { chain ->
            val request: Request = chain.request()
            chain.proceed(request.newBuilder().build())
        }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideDetailsApi(retrofit: Retrofit): DetailsApi =
        retrofit.create(DetailsApi::class.java)

    @Provides
    fun provideProductsApi(retrofit: Retrofit): ProductApi =
        retrofit.create(ProductApi::class.java)

    @Provides
    fun provideDetailsRemoteMapper(): RemoteMapper<DetailsResponse, Details> = DetailsRemoteMapper()

    @Provides
    fun provideProductRemoteMapper(): RemoteMapper<ProductRemote, Product> = ProductRemoteMapper()


}