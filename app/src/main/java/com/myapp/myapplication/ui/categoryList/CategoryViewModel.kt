package com.myapp.myapplication.ui.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.repo.category.CategoryRepository
import com.myapp.myapplication.repo.category.TmdbGenre
import com.myapp.myapplication.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository,
    dispatcher: CoroutineDispatcher,
    private val navigationManager: NavigationManager
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

    fun onCategoryClicked(genreId: Int) {
        navigationManager.navigateToSelectedCategory(genreId)
    }
}

