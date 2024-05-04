package com.example.home.data.utils

import android.content.Context
import javax.inject.Singleton

@Singleton
object ApplicationContextSingleton {

    lateinit var appApplicationContext: Context

    fun initialize(application: Context) {
        appApplicationContext = application
    }

    fun getString(stringId: Int): String {
        return appApplicationContext.getString(stringId)
    }

    fun getString(stringId: Int, stringArgument: String): String {
        return appApplicationContext.getString(stringId, stringArgument)
    }

}