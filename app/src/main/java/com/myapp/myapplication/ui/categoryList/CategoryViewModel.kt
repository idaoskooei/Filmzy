package com.myapp.myapplication.ui.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.ui.categoryList.repo.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val repository: CategoryRepository,
    dispatcher: CoroutineDispatcher
) : ViewModel() {

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
        viewModelScope.launch(dispatcher) {
            _uiState.update { currentState ->
                currentState.copy(
                    genres = repository.getGenres()
                )
            }
        }
    }

    companion object {
        fun provideFactory(
            categoryRepository: CategoryRepository,
            dispatcher: CoroutineDispatcher
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CategoryViewModel(categoryRepository, dispatcher) as T
            }
        }
    }
}

