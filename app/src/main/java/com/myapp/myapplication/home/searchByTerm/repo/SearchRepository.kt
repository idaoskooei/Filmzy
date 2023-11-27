package com.myapp.myapplication.home.searchByTerm.repo

import com.myapp.myapplication.model.Movie
import com.myapp.myapplication.model.TVShow

class SearchRepository(private val remoteService: SearchRemoteService) {
    suspend fun searchMovies(searchTerm: String): List<Movie> {
        return remoteService.searchMovies(searchTerm).results
    }

    suspend fun searchMoviesByGenre(genre: Int): List<Movie> {
        return remoteService.searchMoviesByGenre(genre).results
    }

    suspend fun searchTvShows(searchTerm: String): List<TVShow> {
        return remoteService.searchTv(searchTerm).results
    }
}