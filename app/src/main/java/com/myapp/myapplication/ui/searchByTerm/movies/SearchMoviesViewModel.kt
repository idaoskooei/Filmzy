package com.myapp.myapplication.ui.searchByTerm.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.PagingData
import com.myapp.myapplication.di.AppModule
import com.myapp.myapplication.model.Movie
import com.myapp.myapplication.repo.search.SearchRepository
import com.myapp.myapplication.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val dispatcher: CoroutineDispatcher,
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val uiState: StateFlow<PagingData<Movie>> = _uiState.asStateFlow()

    fun onSearchClicked(searchTerm: String) {
        viewModelScope.launch(dispatcher) {
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
                navigationManager.navigateToMovieDetails(movieDetails.movieId)
            } catch (e: Exception) {
                Log.e("wtf", "something wrong when searching movies")
            }
        }
    }
}

