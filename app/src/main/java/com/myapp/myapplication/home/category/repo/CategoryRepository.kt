package com.myapp.myapplication.home.category.repo

import com.myapp.myapplication.home.category.TmdbGenre

class CategoryRepository(private val remoteService: CategoryRemoteService) {
    suspend fun getGenres(): List<TmdbGenre> {
        return remoteService.getMovieGenres().genres
    }
}