package com.myapp.myapplication.ui.searchByCategory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.myapp.myapplication.model.Movie
import com.myapp.myapplication.repo.search.SearchRepository
import com.myapp.myapplication.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: SearchRepository,
    val dispatcher: CoroutineDispatcher,
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val uiState: StateFlow<PagingData<Movie>> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<Int>(GENRE_ID_ARG)?.let { genre ->
            viewModelScope.launch(dispatcher) {
                try {
                    repository.searchMoviesByGenre(genre).collectLatest { pagingData ->
                        _uiState.value = pagingData
                    }
                } catch (e: Exception) {
                    println(e.message)
                }
            }
        } ?: {
            throw IllegalArgumentException("movie list needs a {genreId} to operate!!")
        }
    }

    fun onMovieClicked(movie: Movie) {
        navigationManager.navigateToMovieDetails(movie.id)
    }

    fun onHomeButtonClicked() {
        navigationManager.navigateToHome()
    }

    fun onBackButtonClicked() {
        navigationManager.navigateToMoviesList()
    }

    companion object {
        const val GENRE_ID_ARG = "genreId"
    }
}