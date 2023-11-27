package com.myapp.myapplication.home.searchByTerm.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.home.searchByTerm.repo.SearchRepository
import com.myapp.myapplication.model.TVShow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchShowsViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _uiState = MutableStateFlow<List<TVShow>>(emptyList())
    val uiState: StateFlow<List<TVShow>> = _uiState.asStateFlow()

    fun onSearchClicked(query: String) {
        viewModelScope.launch {
            try {
                _uiState.value = repository.searchTvShows(searchTerm = query)
                _errorMessage.value = ""
            } catch (e: Exception) {
                handleError(e)
                _uiState.value = emptyList()
            }
        }
    }

    fun onItemClicked() {
        TODO()
    }

    private fun handleError(error: Throwable) {
        _errorMessage.value = "An error occurred: ${error.message}"
    }
}