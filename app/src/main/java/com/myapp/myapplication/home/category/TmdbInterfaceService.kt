package com.myapp.myapplication.home.category

import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryRemoteService {
    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): TmdbGenreResponse
}