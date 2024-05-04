package com.example.home.data.networking.interceptor

import android.content.Context
import com.example.home.R
import java.io.IOException

class NoConnectivityException(private val context: Context) : IOException() {
    override val message: String
        get() = context.getString(R.string.no_internet_connection_error)
}