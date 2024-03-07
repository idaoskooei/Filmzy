package com.myapp.myapplication.ui

import androidx.lifecycle.ViewModel
import com.myapp.myapplication.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun onSearchMoviesButtonClicked() {
        navigationManager.navigateToMoviesScreenResult()
    }

    fun onSearchShowsButtonClicked() {
        navigationManager.navigateToShowsScreenResult()
    }

    fun onCategoryButtonClicked() {
        navigationManager.navigateToCategoryScreen()
    }

    fun onPickRandomButtonClicked() {
        navigationManager.navigateToRandomMovieScreen()
    }
}