package com.myapp.myapplication.profile

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class ProfileViewModel(private val navController: NavController) : ViewModel() {

    fun onProfileButtonClicked() {
        navController.navigate("home_screen")
    }
}