package com.myapp.myapplication.home.category.repo

import com.myapp.myapplication.home.category.TmdbGenreResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryRemoteService {
    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("language") language: String = "en-US"
    ): TmdbGenreResponse
}