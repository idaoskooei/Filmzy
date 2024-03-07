package com.myapp.myapplication.di

import android.app.Application
import coil.Coil
import coil.ImageLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Coil.setImageLoader {
            ImageLoader.Builder(this)
                .crossfade(true)
                .build()
        }
    }
}