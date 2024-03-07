package com.myapp.myapplication.ui.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.ui.BaseFilmzyViewModel
import com.myapp.myapplication.ui.categoryList.repo.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModelBase(
    private val repository: CategoryRepository
) : BaseFilmzyViewModel() {

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
        viewModelScope.launch(Dispatchers.IO){
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
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CategoryViewModelBase(categoryRepository) as T
            }
        }
    }
}

