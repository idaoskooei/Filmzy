package com.myapp.myapplication.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.home.search.repo.SearchRepository
import com.myapp.myapplication.movie.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _uiState = MutableStateFlow<List<Movie>>(emptyList())
    val uiState: StateFlow<List<Movie>> = _uiState.asStateFlow()

    fun onSearchClicked(query: String) {
        viewModelScope.launch {
            try {
                _uiState.value = repository.searchMovies(query)
                _errorMessage.value = ""
            } catch (e: Exception) {
                handleError(e)
                _uiState.value = emptyList()
            }
        }
    }

    fun onItemClicked(){
        TODO()
    }

    private fun handleError(error: Throwable) {
        _errorMessage.value = "An error occurred: ${error.message}"
    }
}

