package com.myapp.myapplication.home.searchByTerm.repo

import com.myapp.myapplication.home.searchByTerm.movies.MovieResponse
import com.myapp.myapplication.home.searchByTerm.shows.TVShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchRemoteService {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = true,
        @Query("page") page: Int
    ): MovieResponse

    @GET("search/tv")
    suspend fun searchTv(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = true,
        @Query("page") page: Int
    ): TVShowResponse

    @GET("discover/movie")
    suspend fun searchMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("include_adult") includeAdult: Boolean = true
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int
    ): MovieResponse
}