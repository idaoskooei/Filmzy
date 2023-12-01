package com.myapp.myapplication.paging

import androidx.paging.PagingConfig

object PagingUtils {
    fun getPagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 20,
            prefetchDistance = 10,
            enablePlaceholders = false
        )
    }
}