package com.myapp.myapplication.home.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryViewModel : ViewModel() {

    private val apiKey = "b374a90d9ab89653cff28333dccd5836"


    private val _categories = MutableStateFlow<List<TmdbGenre>>(emptyList())
    val categories: StateFlow<List<TmdbGenre>> get() = _categories

    fun fetchGenres() {
        viewModelScope.launch {
            try {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                val tmdbApiService = retrofit.create(TmdbApiService::class.java)

                val response = tmdbApiService.getMovieGenres(apiKey)
                _categories.value = response.genres
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}

