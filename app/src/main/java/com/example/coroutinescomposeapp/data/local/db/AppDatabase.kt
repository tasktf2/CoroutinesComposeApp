package com.example.coroutinescomposeapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coroutinescomposeapp.data.local.db.dao.UserDao
import com.example.coroutinescomposeapp.data.local.model.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "users.db"

        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {

            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .build()
                .also { INSTANCE = it }
        }
    }
}