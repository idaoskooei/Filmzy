package com.myapp.myapplication.repo.search

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.myapp.myapplication.model.Movie
import com.myapp.myapplication.model.TVShow
import com.myapp.myapplication.paging.MoviesPagingSource
import com.myapp.myapplication.paging.PagingUtils.getPagingConfig
import com.myapp.myapplication.paging.ShowPagingSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(private val remoteService: SearchRemoteService) {
    @OptIn(DelicateCoroutinesApi::class)
    fun getMoviesSearchResults(searchTerm: String): Flow<PagingData<Movie>> =
        Pager(config = getPagingConfig()) {
//            MoviePagingSource(remoteService, searchTerm)
            MoviesPagingSource { currentPage ->
                remoteService.searchMovies(
                    query = searchTerm, page = currentPage,
//                includeAdult = true
                )
            }

        }.flow.cachedIn(GlobalScope)

    @OptIn(DelicateCoroutinesApi::class)
    fun getShowsSearchResults(searchTerm: String): Flow<PagingData<TVShow>> =
        Pager(config = getPagingConfig()) {
            ShowPagingSource(remoteService, searchTerm)
        }.flow.cachedIn(GlobalScope)

    @OptIn(DelicateCoroutinesApi::class)
    fun searchMoviesByGenre(id: Int): Flow<PagingData<Movie>> = Pager(config = getPagingConfig()) {
//        MoviesByGenrePagingSource(remoteService, id = id)
        MoviesPagingSource { currentPage ->
            remoteService.searchMoviesByGenre(
                page = currentPage,
                genreId = id,
                includeAdult = false
            )
        }
    }.flow.cachedIn(GlobalScope)

    suspend fun getMovieDetails(id: Int): MovieResponse {
        return remoteService.getMovieDetails(id)
    }

    suspend fun getTvShowDetails(id: Int): TVShowResponse {
        return remoteService.getTvShowDetails(id)
    }

    suspend fun getRandomMovie(): MovieResponse {
        val latestMovie = remoteService.getLatestMovie()

        val randomNumber = (1..latestMovie.movieId).random()

        return remoteService.getMovieDetails(randomNumber)
    }
}