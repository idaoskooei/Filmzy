package com.myapp.myapplication.home.search

import android.app.DownloadManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.myapplication.movie.BASE_URL
import com.myapp.myapplication.movie.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchViewModel : ViewModel() {

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()


    private val apiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SearchRemoteService::class.java)

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                val response = apiService.searchMovies(query)
                _movies.value = response.results
                _errorMessage.value = ""
            } catch (e: Exception) {
                handleError(e)
                _movies.value = emptyList()
            }
        }
    }

    private fun handleError(error: Throwable) {
        _errorMessage.value = "An error occurred: ${error.message}"
    }
}


