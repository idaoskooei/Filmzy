package com.myapp.myapplication.home

import androidx.lifecycle.ViewModel
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

    fun onProfileButtonClicked() {
        navController.navigate(Destinations.PROFILE_ROUTE)
    }
}