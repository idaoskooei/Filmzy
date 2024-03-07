package com.myapp.myapplication.repo.category

class CategoryRepository(private val remoteService: CategoryRemoteService) {
    suspend fun getGenres(): List<TmdbGenre> {
        return remoteService.getMovieGenres().genres
    }
}