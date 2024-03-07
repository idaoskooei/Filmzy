package com.myapp.myapplication.ui.randomPick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.FilmzyExceptions
import com.myapp.myapplication.repo.search.MovieResponse
import com.myapp.myapplication.repo.search.SearchRepository
import com.myapp.myapplication.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomMovieViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val errorMessage = when (throwable) {
            FilmzyExceptions.MovieNotFound -> "we were not able to find a movie for you. try again!"
            FilmzyExceptions.GeneralError -> "Oops. something went wrong. try again!"

            else -> {
                "Oops. something went wrong. try again!"
            }
        }
        _uiState.update { currentState ->
            currentState.copy(
                errorMessage = errorMessage
            )
        }
    }

    data class UiState(
        val errorMessage: String? = null, val randomMovie: MovieResponse? = null
    )

    init {
        viewModelScope.launch(exceptionHandler) {
            try {
                val movie = repository.getRandomMovie()
                _uiState.update { currentState ->
                    currentState.copy(
                        randomMovie = movie
                    )
                }

            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        errorMessage = "Error fetching movie: ${e.message}"
                    )
                }
            }
        }
    }

    fun onBackClicked() {
        navigationManager.navigateToCategoryList()
    }
}