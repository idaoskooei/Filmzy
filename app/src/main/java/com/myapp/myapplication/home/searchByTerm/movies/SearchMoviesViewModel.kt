package com.myapp.myapplication.home.searchByTerm.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.PagingData
import com.myapp.myapplication.FilmzyViewModel
import com.myapp.myapplication.home.searchByTerm.repo.SearchRepository
import com.myapp.myapplication.model.Movie
import com.myapp.myapplication.navigation.Destinations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchMoviesViewModel(
    private val repository: SearchRepository,
    private val navController: NavController
) : FilmzyViewModel() {

    private val _uiState = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val uiState: StateFlow<PagingData<Movie>> = _uiState.asStateFlow()

    fun onSearchClicked(searchTerm: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (searchTerm.isNotBlank()) {
                    repository.getMoviesSearchResults(searchTerm).collectLatest { pagingData ->
                        _uiState.value = pagingData
                    }
                } else {
                    Log.d("wtf", "Search term is empty")
                }
            } catch (e: Exception) {
                Log.e("wtf", "something wrong when clicked on search in search movies screen")
            }
        }
    }

    fun onMovieClicked(movie: Movie) {
        viewModelScope.launch{
            try {
                val movieDetails = repository.getMovieDetails(movie.id)
                navController.navigate("${Destinations.MOVIE_DETAIL_SCREEN}/${movieDetails.movieId}")
            } catch (e: Exception) {
                Log.e("wtf", "something wrong when searching movies")
            }
        }
    }

    companion object {
        fun provideFactory(
            repository: SearchRepository,
            navController: NavController
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SearchMoviesViewModel(repository, navController) as T
            }
        }
    }
}

