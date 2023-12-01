package com.myapp.myapplication.home.searchByTerm.repo

import androidx.paging.Pager
import androidx.paging.PagingData
import com.myapp.myapplication.home.searchByTerm.movies.MovieResponse
import com.myapp.myapplication.home.searchByTerm.shows.TVShowResponse
import com.myapp.myapplication.model.Movie
import com.myapp.myapplication.model.TVShow
import com.myapp.myapplication.paging.MoviePagingSource
import com.myapp.myapplication.paging.MoviesByGenrePagingSource
import com.myapp.myapplication.paging.PagingUtils.getPagingConfig
import com.myapp.myapplication.paging.TVShowPagingSource
import kotlinx.coroutines.flow.Flow

class SearchRepository(private val remoteService: SearchRemoteService) {

    fun getMoviesSearchResults(searchTerm: String): Flow<PagingData<Movie>> =
        Pager(config = getPagingConfig()) {
            MoviePagingSource(remoteService, searchTerm)
        }.flow

    fun getShowsSearchResults(searchTerm: String): Flow<PagingData<TVShow>> =
        Pager(config = getPagingConfig()) {
            TVShowPagingSource(remoteService, searchTerm)
        }.flow

    fun searchMoviesByGenre(id: Int): Flow<PagingData<Movie>> =
        Pager(config = getPagingConfig()) {
            MoviesByGenrePagingSource(remoteService, id = id)
        }.flow

    suspend fun getMovieDetails(id: Int): MovieResponse {
        return remoteService.getMovieDetails(id)
    }

    suspend fun getTvShowDetails(id: Int): TVShowResponse {
        return remoteService.getTvShowDetails(id)
    }
}