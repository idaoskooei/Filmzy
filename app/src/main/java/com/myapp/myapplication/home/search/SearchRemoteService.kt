package com.myapp.myapplication.home.search

import com.myapp.myapplication.movie.API_KEY
import com.myapp.myapplication.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteService {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY,
    ): MovieResponse
}