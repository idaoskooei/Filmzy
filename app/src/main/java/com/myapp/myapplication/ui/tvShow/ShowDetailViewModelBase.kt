package com.myapp.myapplication.ui.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.ui.BaseFilmzyViewModel
import com.myapp.myapplication.repo.SearchRepository
import com.myapp.myapplication.repo.TVShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShowDetailViewModelBase(
    private val repository: SearchRepository,
    private val id: Int
) : BaseFilmzyViewModel() {

    private val _uiState = MutableStateFlow<TVShowResponse?>(null)
    val uiState: StateFlow<TVShowResponse?> = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
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
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ShowDetailViewModelBase(repository, id) as T
            }
        }
    }
}