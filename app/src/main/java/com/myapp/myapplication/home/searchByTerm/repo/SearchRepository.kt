package com.myapp.myapplication.home.searchByTerm.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.myapp.myapplication.model.Movie
import com.myapp.myapplication.model.TVShow
import com.myapp.myapplication.paging.MoviePagingSource
import com.myapp.myapplication.paging.TVShowPagingSource
import kotlinx.coroutines.flow.Flow

class SearchRepository(private val remoteService: SearchRemoteService) {

    fun getMoviesSearchResults(searchTerm: String): Flow<PagingData<Movie>> =
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 10, enablePlaceholders = false)) {
            MoviePagingSource(remoteService, searchTerm)
        }.flow

    fun getShowsSearchResults(searchTerm: String): Flow<PagingData<TVShow>> =
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 10, enablePlaceholders = false)) {
            TVShowPagingSource(remoteService, searchTerm)
        }.flow

    suspend fun searchMoviesByGenre(genre: Int): List<Movie> {
        return remoteService.searchMoviesByGenre(genreId = genre).results
    }
}