package com.example.coroutinescomposeapp.di

import android.content.Context
import com.example.coroutinescomposeapp.ComposeApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideContext(): Context = ComposeApplication.appContext!!
}