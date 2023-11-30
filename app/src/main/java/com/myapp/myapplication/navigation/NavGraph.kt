package com.myapp.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myapp.myapplication.auth.IntroScreen
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
import com.myapp.myapplication.home.searchByCategory.MovieListScreen
import com.myapp.myapplication.home.searchByCategory.MovieListViewModel
import com.myapp.myapplication.home.searchByTerm.movies.SearchMoviesScreen
import com.myapp.myapplication.home.searchByTerm.movies.SearchMoviesViewModel
import com.myapp.myapplication.home.searchByTerm.repo.SearchRemoteService
import com.myapp.myapplication.home.searchByTerm.repo.SearchRepository
import com.myapp.myapplication.home.searchByTerm.shows.SearchShowsScreen
import com.myapp.myapplication.home.searchByTerm.shows.SearchShowsViewModel
import com.myapp.myapplication.movie.MovieDetailsScreen
import com.myapp.myapplication.movie.MovieDetailsViewModel
import com.myapp.myapplication.profile.ProfileScreen
import com.myapp.myapplication.profile.ProfileViewModel
import com.myapp.myapplication.retrofit
import com.myapp.myapplication.tvShow.ShowDetailScreen
import com.myapp.myapplication.tvShow.ShowDetailViewModel
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
        composable(Destinations.SEARCH_MOVIES_SCREEN_ROUTE) {
            SearchMoviesScreen(
                viewModel = SearchMoviesViewModel(
                    SearchRepository(
                        retrofit.create(
                            SearchRemoteService::class.java
                        )
                    ), navController = navController
                )
            )
        }
        composable(Destinations.SEARCH_SHOWS_SCREEN_ROUTE) {
            SearchShowsScreen(
                viewModel = SearchShowsViewModel(
                    SearchRepository(
                        retrofit.create(
                            SearchRemoteService::class.java
                        )
                    ),
                    navController = navController
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
        composable(route = "movie_detail_screen/{id}", arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: 0

            if (id == 0) {
                throw IllegalArgumentException("MovieDetailScreen needs an {id} to operate!!")
            } else {
                MovieDetailsScreen(
                    viewModel = MovieDetailsViewModel(
                        SearchRepository(
                            retrofit.create(SearchRemoteService::class.java)
                        ),
                        id = id
                    )
                )
            }
        }
        composable(route = "show_detail_screen/{id}", arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: 0

            if (id == 0) {
                throw IllegalArgumentException("TvShow detail screen needs an {id} to operate!!")
            } else {
                ShowDetailScreen(
                    viewModel = ShowDetailViewModel(
                        SearchRepository(
                            retrofit.create(SearchRemoteService::class.java)
                        ),
                        id = id
                    )
                )
            }
        }
    }
}