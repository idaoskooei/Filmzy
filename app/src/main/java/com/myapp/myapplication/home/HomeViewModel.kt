package com.myapp.myapplication.home

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class HomeViewModel(
    private val navController: NavController
) : ViewModel() {

    fun onSearchButtonClicked() {
        navController.navigate("search_screen")
    }
}