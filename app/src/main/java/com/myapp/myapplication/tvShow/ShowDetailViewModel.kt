package com.myapp.myapplication.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.home.searchByTerm.repo.SearchRepository
import com.myapp.myapplication.home.searchByTerm.shows.TVShowResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShowDetailViewModel(
    private val repository: SearchRepository, private val id: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow<TVShowResponse?>(null)
    val uiState: StateFlow<TVShowResponse?> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val details = repository.getTvShowDetails(id)
                _uiState.value = details
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}