package com.myapp.myapplication.home.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.home.categoryList.repo.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UiState()
    )

    data class UiState(
        val errorMessage: String? = null,
        val genres: List<TmdbGenre> = emptyList()
    )

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    genres = repository.getGenres()
                )
            }
        }
    }
}

