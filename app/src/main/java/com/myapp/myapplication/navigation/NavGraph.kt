package com.myapp.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myapp.myapplication.IntroScreen
import com.myapp.myapplication.auth.model.AuthRepository
import com.myapp.myapplication.auth.signin.SignInScreen
import com.myapp.myapplication.auth.signin.SignInViewModel
import com.myapp.myapplication.auth.signup.SignUpScreen
import com.myapp.myapplication.auth.signup.SignUpViewModel
import com.myapp.myapplication.home.HomeScreen
import com.myapp.myapplication.home.HomeViewModel
import com.myapp.myapplication.home.categoryList.CategoryScreen
import com.myapp.myapplication.home.categoryList.CategoryViewModel
import com.myapp.myapplication.home.categoryList.repo.CategoryRemoteService
import com.myapp.myapplication.home.categoryList.repo.CategoryRepository
import com.myapp.myapplication.home.search.SearchScreen
import com.myapp.myapplication.home.search.SearchViewModel
import com.myapp.myapplication.home.search.repo.SearchRemoteService
import com.myapp.myapplication.home.search.repo.SearchRepository
import com.myapp.myapplication.home.searchByCategory.MovieListScreen
import com.myapp.myapplication.home.searchByCategory.MovieListViewModel
import com.myapp.myapplication.profile.ProfileScreen
import com.myapp.myapplication.profile.ProfileViewModel
import com.myapp.myapplication.retrofit
import retrofit2.create

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    authRepository: AuthRepository = AuthRepository()
) {
    val startDestination = if (authRepository.isUserSignedIn()) {
        Destinations.HOME_ROUTE
    } else {
        Destinations.INTRO_ROUTE
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.INTRO_ROUTE) {
            IntroScreen(navController = navController)
        }
        composable(Destinations.SIGN_IN_ROUTE) {
            SignInScreen(
                viewModel = SignInViewModel(
                    authRepository = AuthRepository(),
                    navController = navController
                )
            )
        }
        composable(Destinations.SIGN_UP_ROUTE) {
            SignUpScreen(
                viewModel = SignUpViewModel(
                    authRepository = AuthRepository(),
                    navController = navController
                )
            )
        }
        composable(Destinations.HOME_ROUTE) {
            HomeScreen(viewModel = HomeViewModel(navController))
        }
        composable(Destinations.PROFILE_ROUTE) {
            ProfileScreen(
                viewModel = ProfileViewModel(
                    navController,
                    authRepository = authRepository
                )
            )
        }
        composable(Destinations.SEARCH_SCREEN_ROUTE) {
            SearchScreen(
                viewModel = SearchViewModel(
                    SearchRepository(
                        retrofit.create(
                            SearchRemoteService::class.java
                        )
                    )
                )
            )
        }
        composable(Destinations.CATEGORY_SCREEN_ROUTE) {
            CategoryScreen(
                navController = navController, viewModel = CategoryViewModel(
                    CategoryRepository(
                        retrofit.create(
                            CategoryRemoteService::class.java
                        )
                    )
                )
            )
        }
        composable("movie_list_screen/{genreId}", arguments = listOf(navArgument("genreId") {
            type = NavType.IntType
        })) { backStackEntry ->

            val genreId =
                backStackEntry.arguments?.getInt("genreId") ?: 0

            if (genreId == 0) {
                throw IllegalArgumentException("MovieListScreen needs a {genreId} to operate!!")
            } else {
                MovieListScreen(
                    onMovieClick = {},
                    viewModel = MovieListViewModel(
                        SearchRepository(retrofit.create()),
                        genre = genreId
                    )
                )
            }
        }
    }
}