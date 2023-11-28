package com.myapp.myapplication.home.searchByTerm.movies

import com.google.gson.annotations.SerializedName
import com.myapp.myapplication.model.Movie

data class MovieResponse(
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("id") val movieId: Int,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("total_results") val totalResults: Int
)