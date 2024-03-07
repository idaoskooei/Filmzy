package com.myapp.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.myapp.myapplication.ui.HomeScreen
import com.myapp.myapplication.ui.IntroScreen
import com.myapp.myapplication.ui.categoryList.CategoryScreen
import com.myapp.myapplication.ui.movieDetail.MovieDetailsScreen
import com.myapp.myapplication.ui.movieDetail.MovieDetailsViewModel.Companion.MOVIE_ID_ARG
import com.myapp.myapplication.ui.randomPick.RandomMovieScreen
import com.myapp.myapplication.ui.searchByCategory.MovieListScreen
import com.myapp.myapplication.ui.searchByCategory.MovieListViewModel.Companion.GENRE_ID_ARG
import com.myapp.myapplication.ui.searchByTerm.movies.SearchMoviesScreen
import com.myapp.myapplication.ui.searchByTerm.shows.SearchShowsScreen
import com.myapp.myapplication.ui.tvShow.ShowDetailScreen
import com.myapp.myapplication.ui.tvShow.ShowDetailViewModel.Companion.SHOW_ID_ARG

@Composable
fun NavGraph(
    navController: NavHostController
) {
    val startDestination = Destinations.INTRO_ROUTE

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.INTRO_ROUTE) {
            IntroScreen(viewModel = hiltViewModel())
        }
        composable(Destinations.HOME_ROUTE) {
            HomeScreen(
                vm = hiltViewModel()
            )
        }
        composable(Destinations.SEARCH_MOVIES_SCREEN_ROUTE) {
            SearchMoviesScreen(
                vm = hiltViewModel()
            )
        }
        composable(Destinations.SEARCH_SHOWS_SCREEN_ROUTE) {
            SearchShowsScreen(
                vm = hiltViewModel()
            )
        }
        composable(Destinations.CATEGORY_SCREEN_ROUTE) {
            CategoryScreen(vm = hiltViewModel())
        }
        composable(
            route = "movie_list_screen/{${GENRE_ID_ARG}}",
            arguments = listOf(navArgument(GENRE_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            MovieListScreen(
                vm = hiltViewModel()
            )
        }
        composable(
            route = "movie_detail_screen/{${MOVIE_ID_ARG}}",
            arguments = listOf(navArgument(MOVIE_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            MovieDetailsScreen(
                vm = hiltViewModel()
            )
        }
        composable(
            route = "show_detail_screen/{${SHOW_ID_ARG}}",
            arguments = listOf(navArgument(SHOW_ID_ARG) {
                type = NavType.IntType
            })
        ) {
            ShowDetailScreen(
                vm = hiltViewModel()
            )
        }

        composable(
            Destinations.NEW_RANDOM_MOVIE_SCREEN
        ) {
            RandomMovieScreen(
                vm = hiltViewModel()
            )
        }
    }
}