package com.myapp.myapplication

import android.app.Application
import com.google.firebase.FirebaseApp

class FireBaseInitiation : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}