package com.myapp.myapplication.home.searchByTerm.repo

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.myapp.myapplication.home.searchByTerm.movies.MovieResponse
import com.myapp.myapplication.home.searchByTerm.shows.TVShowResponse
import com.myapp.myapplication.model.Movie
import com.myapp.myapplication.model.TVShow
import com.myapp.myapplication.paging.MoviePagingSource
import com.myapp.myapplication.paging.MoviesByGenrePagingSource
import com.myapp.myapplication.paging.PagingUtils.getPagingConfig
import com.myapp.myapplication.paging.ShowPagingSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow

class SearchRepository(private val remoteService: SearchRemoteService) {
    @OptIn(DelicateCoroutinesApi::class)
    fun getMoviesSearchResults(searchTerm: String): Flow<PagingData<Movie>> =
        Pager(config = getPagingConfig()) {
            MoviePagingSource(remoteService, searchTerm)
        }.flow.cachedIn(GlobalScope)

    @OptIn(DelicateCoroutinesApi::class)
    fun getShowsSearchResults(searchTerm: String): Flow<PagingData<TVShow>> =
        Pager(config = getPagingConfig()) {
            ShowPagingSource(remoteService, searchTerm)
        }.flow.cachedIn(GlobalScope)

    @OptIn(DelicateCoroutinesApi::class)
    fun searchMoviesByGenre(id: Int): Flow<PagingData<Movie>> = Pager(config = getPagingConfig()) {
        MoviesByGenrePagingSource(remoteService, id = id)
    }.flow.cachedIn(GlobalScope)

    suspend fun getMovieDetails(id: Int): MovieResponse {
        return remoteService.getMovieDetails(id)
    }

    suspend fun getTvShowDetails(id: Int): TVShowResponse {
        return remoteService.getTvShowDetails(id)
    }

    suspend fun getMovieRecommendationByGenre(id: Int): MovieResponse {
        return remoteService.getMovieRecommendationByGenre(genreId = id, includeAdult = true)
    }
}