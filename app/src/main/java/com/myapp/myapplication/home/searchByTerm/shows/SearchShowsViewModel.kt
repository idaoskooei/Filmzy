package com.myapp.myapplication.home.searchByTerm.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.myapp.myapplication.home.searchByTerm.repo.SearchRepository
import com.myapp.myapplication.model.TVShow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchShowsViewModel(private val repository: SearchRepository) : ViewModel() {

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _uiState = MutableStateFlow<PagingData<TVShow>>(PagingData.empty())
    val uiState: StateFlow<PagingData<TVShow>> = _uiState.asStateFlow()

    fun onSearchClicked(searchTerm: String) {
        viewModelScope.launch {
            try {
                repository.getShowsSearchResults(searchTerm = searchTerm)
                    .collectLatest { pagingData ->
                        _uiState.value = pagingData
                    }
            } catch (e: Exception) {
                handleError(e)
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