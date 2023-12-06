package com.myapp.myapplication.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.myapp.myapplication.navigation.Destinations

class HomeViewModel(
    private val navController: NavController
) : ViewModel() {

    fun onSearchMoviesButtonClicked() {
        navController.navigate(Destinations.SEARCH_MOVIES_SCREEN_ROUTE)
    }

    fun onSearchShowsButtonClicked() {
        navController.navigate(Destinations.SEARCH_SHOWS_SCREEN_ROUTE)
    }

    fun onCategoryButtonClicked() {
        navController.navigate(Destinations.CATEGORY_SCREEN_ROUTE)
    }

    fun onPickRandomButtonClicked() {
        navController.navigate(Destinations.CATEGORY_PICKER_SCREEN)
    }

    fun onProfileButtonClicked() {
        navController.navigate(Destinations.PROFILE_ROUTE)
    }

    companion object{
        fun provideFactory(
            navController: NavController
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(
                    navController = navController
                ) as T
            }
        }
    }

}