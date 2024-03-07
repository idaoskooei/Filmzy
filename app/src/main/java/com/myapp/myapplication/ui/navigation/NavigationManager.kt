package com.myapp.myapplication.ui.navigation

import androidx.navigation.NavHostController

interface NavigationManager {
    fun navigateToMovieDetails(movieId: Int)
    fun navigateToHome()
    fun navigateToSelectedCategory(genreId: Int)
    fun navigateToMoviesScreenResult()
    fun navigateToShowsScreenResult()
    fun navigateToCategoryScreen()
    fun navigateToRandomMovieScreen()
    fun navigateToMoviesList()
    fun navigateToCategoryList()
    fun navigateToShowListResults()
    fun navigateToShowDetails(showId: Int)
}

class NavigationManagerImpl : NavigationManager {

    private lateinit var navController: NavHostController
    fun setup(navHostController: NavHostController) {
        this.navController = navHostController
    }

    override fun navigateToMovieDetails(movieId: Int) {
        navController.navigate("${Destinations.MOVIE_DETAIL_SCREEN}/${movieId}")
    }

    override fun navigateToHome() {
        navController.navigate(Destinations.HOME_ROUTE)
    }

    override fun navigateToSelectedCategory(genreId: Int) {
        navController.navigate("movie_list_screen/$genreId")
    }

    override fun navigateToMoviesScreenResult() {
        navController.navigate(Destinations.SEARCH_MOVIES_SCREEN_ROUTE)
    }

    override fun navigateToShowsScreenResult() {
        navController.navigate(Destinations.SEARCH_SHOWS_SCREEN_ROUTE)
    }

    override fun navigateToCategoryScreen() {
        navController.navigate(Destinations.CATEGORY_SCREEN_ROUTE)
    }

    override fun navigateToRandomMovieScreen() {
        navController.navigate(Destinations.NEW_RANDOM_MOVIE_SCREEN)
    }

    override fun navigateToMoviesList() {
        navController.popBackStack()
    }

    override fun navigateToCategoryList() {
        navController.popBackStack()
    }

    override fun navigateToShowListResults() {
        navController.popBackStack()
    }

    override fun navigateToShowDetails(showId: Int) {
        navController.navigate("${Destinations.TV_SHOW_DETAIL_SCREEN}/${showId}")
    }
}