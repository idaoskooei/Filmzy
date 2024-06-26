package com.myapp.myapplication.ui.tvShow

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.repo.search.SearchRepository
import com.myapp.myapplication.repo.search.TVShowResponse
import com.myapp.myapplication.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailViewModel @Inject constructor(
    private val repository: SearchRepository,
    val dispatcher: CoroutineDispatcher,
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<TVShowResponse?>(null)
    val uiState: StateFlow<TVShowResponse?> = _uiState.asStateFlow()

    init {
        savedStateHandle.get<Int>(SHOW_ID_ARG)?.let { id ->
            viewModelScope.launch(dispatcher) {
                try {
                    val details = repository.getTvShowDetails(id)
                    _uiState.value = details
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } ?: {
            throw IllegalArgumentException("ShowDetailScreen needs an {id} to operate!!")
        }
    }

    fun onBackButtonClicked() {
        navigationManager.navigateToShowListResults()
    }

    companion object {
        const val SHOW_ID_ARG = "showId"
    }
}