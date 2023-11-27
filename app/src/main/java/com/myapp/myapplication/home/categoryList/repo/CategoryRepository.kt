package com.myapp.myapplication.home.categoryList.repo

import com.myapp.myapplication.home.categoryList.TmdbGenre

class CategoryRepository(private val remoteService: CategoryRemoteService) {
    suspend fun getGenres(): List<TmdbGenre> {
        return remoteService.getMovieGenres().genres
    }
}