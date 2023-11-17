package com.myapp.myapplication.profile

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.myapp.myapplication.auth.model.AuthRepository

class ProfileViewModel(
    private val navController: NavController,
    private val authRepository: AuthRepository
) : ViewModel() {

    fun onProfileButtonClicked() {
        navController.navigate("home_screen")
    }

    fun onSignOutButtonClicked() {
        authRepository.signOut()
        navController.navigate("intro_screen")
    }
}