package com.myapp.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myapp.myapplication.repo.SearchRemoteService
import com.myapp.myapplication.repo.SearchRepository
import com.myapp.myapplication.retrofit
import com.myapp.myapplication.ui.HomeScreen
import com.myapp.myapplication.ui.HomeViewModel
import com.myapp.myapplication.ui.IntroScreen
import com.myapp.myapplication.ui.categoryList.CategoryScreen
import com.myapp.myapplication.ui.categoryList.CategoryViewModel
import com.myapp.myapplication.ui.categoryList.repo.CategoryRemoteService
import com.myapp.myapplication.ui.categoryList.repo.CategoryRepository
import com.myapp.myapplication.ui.movie.MovieDetailsScreen
import com.myapp.myapplication.ui.movie.MovieDetailsViewModel
import com.myapp.myapplication.ui.randomPick.CategoryPicker
import com.myapp.myapplication.ui.randomPick.RandomMovieScreen
import com.myapp.myapplication.ui.randomPick.RandomMovieViewModel
import com.myapp.myapplication.ui.searchByCategory.MovieListScreen
import com.myapp.myapplication.ui.searchByCategory.MovieListViewModel
import com.myapp.myapplication.ui.searchByTerm.movies.SearchMoviesScreen
import com.myapp.myapplication.ui.searchByTerm.movies.SearchMoviesViewModel
import com.myapp.myapplication.ui.searchByTerm.shows.SearchShowsScreen
import com.myapp.myapplication.ui.searchByTerm.shows.SearchShowsViewModel
import com.myapp.myapplication.ui.tvShow.ShowDetailScreen
import com.myapp.myapplication.ui.tvShow.ShowDetailViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.create

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    val startDestination = Destinations.INTRO_ROUTE

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.INTRO_ROUTE) {
            IntroScreen(navController = navController)
        }
        composable(Destinations.HOME_ROUTE) {
            HomeScreen(
                viewModel = viewModel(
                    factory = HomeViewModel.provideFactory(
                        navController
                    )
                )
            )
        }
        composable(Destinations.SEARCH_MOVIES_SCREEN_ROUTE) {
            SearchMoviesScreen(
                viewModel = viewModel(
                    factory = SearchMoviesViewModel.provideFactory(
                        navController = navController,
                        repository = SearchRepository(
                            retrofit.create(
                                SearchRemoteService::class.java
                            )
                        ),
                        dispatcher = dispatcher
                    )
                )
            )
        }
        composable(Destinations.SEARCH_SHOWS_SCREEN_ROUTE) {
            SearchShowsScreen(
                viewModel = viewModel(
                    factory = SearchShowsViewModel.provideFactory(
                        repository = SearchRepository(
                            retrofit.create(
                                SearchRemoteService::class.java
                            )
                        ),
                        navController = navController,
                        dispatcher = dispatcher
                    )
                )
            )
        }
        composable(Destinations.CATEGORY_SCREEN_ROUTE) {
            CategoryScreen(
                navController = navController, viewModel = viewModel(
                    factory = CategoryViewModel.provideFactory(
                        CategoryRepository(
                            retrofit.create(
                                CategoryRemoteService::class.java
                            )
                        ),
                        dispatcher = dispatcher
                    )
                )
            )
        }
        composable(Destinations.CATEGORY_PICKER_SCREEN) {
            CategoryPicker(
                viewModel = viewModel(
                    factory = CategoryViewModel.provideFactory(
                        categoryRepository = CategoryRepository(
                            remoteService = retrofit.create(
                                CategoryRemoteService::class.java
                            )
                        ),
                        dispatcher = dispatcher
                    )
                ),
                navController = navController
            )
        }
        composable(
            "${Destinations.RANDOM_MOVIE_SCREEN}/{genreId}",
            arguments = listOf(navArgument("genreId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val genreId = backStackEntry.arguments?.getInt("genreId") ?: 0
            if (genreId == 0) {
                throw IllegalArgumentException("random movie screen needs a {genreId} to operate!!")
            } else {
                RandomMovieScreen(
                    viewModel = viewModel(
                        factory = RandomMovieViewModel.provideFactory(
                            repository = SearchRepository(
                                retrofit.create(SearchRemoteService::class.java)
                            ),
                            dispatcher = dispatcher
                        )
                    ),
                    categoryId = genreId,
                    navController = navController
                )
            }
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
                    viewModel = viewModel(
                        factory = MovieListViewModel.provideFactory(
                            repository = SearchRepository(
                                retrofit.create()
                            ),
                            navController = navController,
                            genre = genreId,
                            dispatcher = dispatcher
                        )
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
                    viewModel = viewModel(
                        factory = MovieDetailsViewModel.provideFactory(
                            id = id,
                            repository = SearchRepository(
                                retrofit.create(SearchRemoteService::class.java)
                            ),
                            dispatcher = dispatcher
                        )
                    ),
                    navController = navController
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
                    viewModel = viewModel(
                        factory = ShowDetailViewModel.provideFactory(
                            repository = SearchRepository(
                                retrofit.create(SearchRemoteService::class.java)
                            ),
                            id = id,
                            dispatcher = dispatcher
                        )
                    ),
                    navController = navController
                )
            }
        }
    }
}