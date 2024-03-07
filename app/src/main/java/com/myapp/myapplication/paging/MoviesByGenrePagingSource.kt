package com.myapp.myapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myapp.myapplication.repo.SearchRemoteService
import com.myapp.myapplication.model.Movie

class MoviesByGenrePagingSource(
    private val remoteService: SearchRemoteService,
    private var id: Int
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val response = remoteService.searchMoviesByGenre(page = currentPage, genreId = id, includeAdult = false)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage < response.totalPages) currentPage + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }
}