package com.myapp.myapplication.repo.category

import com.myapp.myapplication.repo.search.MovieResponse
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val remoteService: CategoryRemoteService) {
    suspend fun getGenres(): List<TmdbGenre> {
        return remoteService.getMovieGenres().genres
    }
}