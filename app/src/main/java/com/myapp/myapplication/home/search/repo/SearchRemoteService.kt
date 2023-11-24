package com.myapp.myapplication.home.search.repo

import com.myapp.myapplication.home.search.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchRemoteService {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
    ): MovieResponse
}