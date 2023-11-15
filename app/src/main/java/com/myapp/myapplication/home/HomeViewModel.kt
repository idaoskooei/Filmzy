package com.myapp.myapplication.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class HomeViewModel(
    private val navController: NavController
) : ViewModel() {

    fun onSearchButtonClicked() {
        navController.navigate("search_screen")
    }

    fun onCategoryButtonClicked() {
        navController.navigate("category_screen")
    }

    fun onProfileButtonClicked() {
        navController.navigate("profile_screen")
    }
}