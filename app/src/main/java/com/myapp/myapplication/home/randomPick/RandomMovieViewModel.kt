package com.myapp.myapplication.home.randomPick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapp.myapplication.FilmzyViewModel
import com.myapp.myapplication.home.searchByTerm.repo.SearchRepository
import com.myapp.myapplication.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class RandomMovieViewModel(
    private val repository: SearchRepository
) : FilmzyViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    data class UiState(
        val errorMessage: String? = null,
        val randomMovie: Movie? = null
    )

    fun pickRandomMovie(categoryId: Int) {
        launch(Dispatchers.IO) {
            try {
                val movies = repository.getMovieRecommendationByGenre(categoryId)

                if (movies.results.isNotEmpty()) {

                    val randomMovie = movies.results.random()

                    _uiState.update { currentState ->
                        currentState.copy(
                            randomMovie = randomMovie
                        )
                    }
                } else {
                    _uiState.update { currentState ->
                        currentState.copy(
                            errorMessage = "No movies found for the selected category"
                        )
                    }
                }

            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        errorMessage = "Error fetching movies: ${e.message}"
                    )
                }
            }
        }
    }

    companion object {
        fun provideFactory(
            repository: SearchRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return RandomMovieViewModel(repository) as T
            }
        }
    }
}

