package com.myapp.myapplication

import android.app.Application
import coil.Coil
import coil.ImageLoader
import com.google.firebase.FirebaseApp

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        Coil.setImageLoader {
            ImageLoader.Builder(this)
                .crossfade(true)
                .build()
        }
    }
}
