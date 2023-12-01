package com.myapp.myapplication.home.searchByCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.myapp.myapplication.home.searchByTerm.repo.SearchRepository
import com.myapp.myapplication.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val repository: SearchRepository,
    private val genre: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val uiState: StateFlow<PagingData<Movie>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                repository.searchMoviesByGenre(genre).collectLatest { pagingData ->
                    _uiState.value = pagingData
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}