package com.myapp.myapplication.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.myapp.myapplication.repo.search.SearchRemoteService
import com.myapp.myapplication.model.TVShow

class ShowPagingSource(
    private val remoteService: SearchRemoteService,
    private var searchTerm: String
) : PagingSource<Int, TVShow>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShow> {
        return try {
            val currentPage = params.key ?: 1
            val response =
                remoteService.searchTv(query = searchTerm, page = currentPage, includeAdult = false)
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

    override fun getRefreshKey(state: PagingState<Int, TVShow>): Int? {
        return null
    }
}