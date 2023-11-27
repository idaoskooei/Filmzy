package com.myapp.myapplication.profile

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.myapp.myapplication.auth.model.AuthRepository
import com.myapp.myapplication.navigation.Destinations

class ProfileViewModel(
    private val navController: NavController,
    private val authRepository: AuthRepository
) : ViewModel() {

    fun onProfileButtonClicked() {
        navController.navigate(Destinations.HOME_ROUTE)
    }

    fun onSignOutButtonClicked() {
        authRepository.signOut()
        navController.navigate(Destinations.INTRO_ROUTE)
    }
}