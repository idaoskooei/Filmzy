package com.myapp.myapplication.ui.categoryList.repo

import com.myapp.myapplication.ui.categoryList.TmdbGenreResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryRemoteService {
    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Query("language") language: String = "en-US"
    ): TmdbGenreResponse
}