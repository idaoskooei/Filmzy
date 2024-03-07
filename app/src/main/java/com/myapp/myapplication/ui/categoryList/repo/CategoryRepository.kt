package com.myapp.myapplication.ui.categoryList.repo

import com.myapp.myapplication.ui.categoryList.TmdbGenre

class CategoryRepository(private val remoteService: CategoryRemoteService) {
    suspend fun getGenres(): List<TmdbGenre> {
        return remoteService.getMovieGenres().genres
    }
}