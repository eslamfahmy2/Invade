package com.example.xmlt

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.example.home.data.utils.ApplicationContextSingleton
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()
        ApplicationContextSingleton.initialize(application = this)
    }
}