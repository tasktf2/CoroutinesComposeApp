package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.base.EntityMapper
import com.example.data.local.db.AppDatabase
import com.example.data.local.model.UserEntity
import com.example.data.local.model.UserEntityMapper
import com.example.domain.model.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object ProvideLocalModule {
    private const val DATABASE_NAME = "users.db"

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, DATABASE_NAME
    ).build()

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Provides
    fun provideUserEntityMapper(): EntityMapper<UserEntity, User> = UserEntityMapper()


}