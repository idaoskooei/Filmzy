package com.myapp.myapplication.home.search.repo

import com.myapp.myapplication.movie.Movie

class SearchRepository(private val remoteService: SearchRemoteService) {
    suspend fun searchMovies(searchTerm: String): List<Movie> {
        return remoteService.searchMovies(searchTerm).results
    }

    suspend fun searchMoviesByGenre(genre: String): List<Movie> {
        return remoteService.searchMoviesByGenre(genre).results
    }
}