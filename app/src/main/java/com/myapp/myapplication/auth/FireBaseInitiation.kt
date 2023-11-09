package com.myapp.myapplication.auth

import android.app.Application
import com.google.firebase.FirebaseApp

class FireBaseInitiation : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}