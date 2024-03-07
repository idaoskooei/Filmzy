package com.myapp.myapplication.ui.searchByCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.PagingData
import com.myapp.myapplication.model.Movie
import com.myapp.myapplication.repo.search.SearchRepository
import com.myapp.myapplication.ui.navigation.Destinations
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: SearchRepository,
    private val genre: Int,
    private val navController: NavController,
    dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val uiState: StateFlow<PagingData<Movie>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            try {
                repository.searchMoviesByGenre(genre).collectLatest { pagingData ->
                    _uiState.value = pagingData
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun onMovieClicked(movie: Movie) {
        viewModelScope.launch {
            try {
                val movieDetails = repository.getMovieDetails(movie.id)
                navController.navigate("${Destinations.MOVIE_DETAIL_SCREEN}/${movieDetails.movieId}")
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    fun onHomeButtonClicked() {
        navController.navigate(Destinations.HOME_ROUTE)
    }

    fun onBackButtonClicked() {
        navController.popBackStack()
    }

    companion object {
        fun provideFactory(
            repository: SearchRepository,
            genre: Int,
            navController: NavController,
            dispatcher: CoroutineDispatcher
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MovieListViewModel(repository, genre, navController, dispatcher) as T
            }
        }
    }
}