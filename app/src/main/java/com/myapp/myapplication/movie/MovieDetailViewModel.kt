package com.myapp.myapplication.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.home.searchByTerm.movies.MovieResponse
import com.myapp.myapplication.home.searchByTerm.repo.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: SearchRepository, private val id: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieResponse?>(null)
    val uiState: StateFlow<MovieResponse?> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val details = repository.getMovieDetails(id)
                _uiState.value = details
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}