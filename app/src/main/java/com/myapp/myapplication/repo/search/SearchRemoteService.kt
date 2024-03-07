package com.myapp.myapplication.repo.search

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchRemoteService {
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
//        @Query("include_adult") includeAdult: Boolean = true,
        @Query("page") page: Int
    ): MovieResponse

    @GET("search/tv")
    suspend fun searchTv(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("page") page: Int
    ): TVShowResponse

    @GET("discover/movie")
    suspend fun searchMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("include_adult") includeAdult: Boolean,
        @Query("page") page: Int
    ): MovieResponse

    @GET("discover/movie")
    suspend fun getMovieRecommendationByGenre(
        @Query("with_genres") genreId: Int,
        @Query("include_adult") includeAdult: Boolean
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int
    ): MovieResponse

    @GET("tv/{series_id}")
    suspend fun getTvShowDetails(
        @Path("series_id") id: Int
    ): TVShowResponse


    @GET("movie/latest")
    suspend fun getLatestMovie(
    ): MovieResponse
}