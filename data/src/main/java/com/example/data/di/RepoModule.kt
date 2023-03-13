package com.example.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.data.local.db.dao.UserDao
import com.example.data.local.model.UserEntityMapper
import com.example.data.remote.api.DetailsApi
import com.example.data.remote.api.ProductApi
import com.example.data.remote.response.DetailsRemoteMapper
import com.example.data.remote.response.ProductRemoteMapper
import com.example.data.repo.DetailsRepoImpl
import com.example.data.repo.ProductRepoImpl
import com.example.data.repo.UserRepoImpl
import com.example.domain.repo.DetailsRepo
import com.example.domain.repo.ProductRepo
import com.example.domain.repo.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module()
@InstallIn(SingletonComponent::class)
object ProvideRepoModule {

    private const val KEY_SHARED_PREFS = "APP_SHARED_PREFS"

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(KEY_SHARED_PREFS, Context.MODE_PRIVATE)

    @Provides
    fun provideUserRepo(
        userDao: UserDao,
        sharedPreferences: SharedPreferences,
        userEntityMapper: UserEntityMapper
    ): UserRepo = UserRepoImpl(userDao, userEntityMapper, sharedPreferences)

    @Provides
    fun provideDetailsRepo(
        detailsApi: DetailsApi,
        detailsRemoteMapper: DetailsRemoteMapper
    ): DetailsRepo = DetailsRepoImpl(detailsApi, detailsRemoteMapper)

    @Provides
    fun provideProductRepo(
        productApi: ProductApi,
        productRemoteMapper: ProductRemoteMapper
    ): ProductRepo = ProductRepoImpl(productApi, productRemoteMapper)

}