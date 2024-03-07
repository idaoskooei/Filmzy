package com.myapp.myapplication.ui.movieDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.repo.search.MovieResponse
import com.myapp.myapplication.repo.search.SearchRepository
import com.myapp.myapplication.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val dispatcher: CoroutineDispatcher,
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    fun onBackButtonClicked() {
        navigationManager.navigateToMoviesList()
    }

    private val _uiState = MutableStateFlow<MovieResponse?>(null)
    val uiState: StateFlow<MovieResponse?> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<Int>(MOVIE_ID_ARG)?.let { id ->
            viewModelScope.launch(dispatcher) {
                try {
                    val details = repository.getMovieDetails(id)
                    _uiState.value = details
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }?: {
            throw IllegalArgumentException("MovieDetailScreen needs an {movieId} to operate!!")
        }
    }

    companion object {
        const val MOVIE_ID_ARG ="movieId"
    }
}