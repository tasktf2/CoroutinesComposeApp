package com.example.coroutinescomposeapp

import android.app.Application
import android.content.Context

class ComposeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        var appContext: Context? = null
    }
}