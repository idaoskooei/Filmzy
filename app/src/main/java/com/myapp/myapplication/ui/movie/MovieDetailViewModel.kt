package com.myapp.myapplication.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapp.myapplication.ui.BaseFilmzyViewModel
import com.myapp.myapplication.repo.MovieResponse
import com.myapp.myapplication.repo.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModelBase(
    private val repository: SearchRepository,
    private val id: Int,
) : BaseFilmzyViewModel() {

    private val _uiState = MutableStateFlow<MovieResponse?>(null)
    val uiState: StateFlow<MovieResponse?> = _uiState.asStateFlow()

    init {
        launch(Dispatchers.IO) {
            try {
                val details = repository.getMovieDetails(id)
                _uiState.value = details
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        fun provideFactory(
            repository: SearchRepository,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MovieDetailsViewModelBase(repository, id) as T
            }
        }
    }
}