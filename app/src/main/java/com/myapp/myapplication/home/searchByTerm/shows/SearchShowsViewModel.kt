package com.myapp.myapplication.home.searchByTerm.shows

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.PagingData
import com.myapp.myapplication.FilmzyViewModel
import com.myapp.myapplication.home.searchByTerm.repo.SearchRepository
import com.myapp.myapplication.model.TVShow
import com.myapp.myapplication.navigation.Destinations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchShowsViewModel(
    private val repository: SearchRepository,
    private val navController: NavController
) : FilmzyViewModel() {

    private val _uiState = MutableStateFlow<PagingData<TVShow>>(PagingData.empty())
    val uiState: StateFlow<PagingData<TVShow>> = _uiState.asStateFlow()

    fun onSearchClicked(searchTerm: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (searchTerm.isNotBlank()) {
                    repository.getShowsSearchResults(searchTerm = searchTerm)
                        .collectLatest { pagingData ->
                            _uiState.value = pagingData
                        }
                } else {
                    Log.d("wtf", "Search term is empty")
                }
            } catch (e: Exception) {
                Log.e("WTF", "something wrong when clicked on search in search shows screen")
            }
        }
    }

    fun onTvShowClicked(tvShow: TVShow) {
        viewModelScope.launch {
            try {
                val showDetails = repository.getTvShowDetails(tvShow.id)
                navController.navigate("${Destinations.TV_SHOW_DETAIL_SCREEN}/${showDetails.id}")
            } catch (e: Exception) {
                Log.e("WTF", "something wrong when clicked on search in search shows screen")
            }
        }
    }

    companion object {
        fun provideFactory(
            repository: SearchRepository,
            navController: NavController
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SearchShowsViewModel(repository, navController) as T
            }
        }
    }
}