package com.myapp.myapplication.home.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.home.search.repo.SearchRepository
import com.myapp.myapplication.movie.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: SearchRepository,
    private val genre: String
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<Movie>>(emptyList())
    val uiState: StateFlow<List<Movie>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _uiState.value = repository.searchMoviesByGenre(genre = genre)
            } catch (e: Exception) {
                // handle error
                _uiState.value = emptyList()
            }
        }
    }
}