package com.myapp.myapplication.ui.searchByTerm.shows

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.myapp.myapplication.model.TVShow
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
class SearchShowsViewModel @Inject constructor(
    private val repository: SearchRepository,
    private val dispatcher: CoroutineDispatcher,
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<TVShow>>(PagingData.empty())
    val uiState: StateFlow<PagingData<TVShow>> = _uiState.asStateFlow()

    fun onSearchClicked(searchTerm: String) {
        viewModelScope.launch(dispatcher) {
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
                navigationManager.navigateToShowDetails(showDetails.id)
            } catch (e: Exception) {
                Log.e("WTF", "something wrong when clicked on search in search shows screen")
            }
        }
    }
}