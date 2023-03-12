package com.example.coroutinescomposeapp

import android.app.Application
import com.example.coroutinescomposeapp.data.local.db.AppDatabase

class ComposeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val appDatabase = AppDatabase.getDatabase(this)
    }
}