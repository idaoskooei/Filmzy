package com.myapp.myapplication.ui.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.repo.search.SearchRepository
import com.myapp.myapplication.repo.search.TVShowResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShowDetailViewModel(
    private val repository: SearchRepository,
    private val id: Int,
    dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<TVShowResponse?>(null)
    val uiState: StateFlow<TVShowResponse?> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            try {
                val details = repository.getTvShowDetails(id)
                _uiState.value = details
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        fun provideFactory(
            repository: SearchRepository,
            id: Int,
            dispatcher: CoroutineDispatcher
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ShowDetailViewModel(repository, id, dispatcher) as T
            }
        }
    }
}