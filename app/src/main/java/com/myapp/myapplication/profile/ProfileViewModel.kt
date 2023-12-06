package com.myapp.myapplication.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.myapp.myapplication.FilmzyViewModel
import com.myapp.myapplication.auth.model.AuthRepository
import com.myapp.myapplication.navigation.Destinations

class ProfileViewModel(
    private val navController: NavController,
    private val authRepository: AuthRepository
) : FilmzyViewModel() {

    fun onProfileButtonClicked() {
        navController.navigate(Destinations.HOME_ROUTE)
    }

    fun onSignOutButtonClicked() {
        authRepository.signOut()
        navController.navigate(Destinations.INTRO_ROUTE)
    }

    companion object {
        fun provideFactory(
            repository: AuthRepository,
            navController: NavController
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProfileViewModel(
                    navController = navController,
                    authRepository = repository
                ) as T
            }
        }
    }
}