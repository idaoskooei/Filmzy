package com.myapp.myapplication.home.searchByTerm.shows

import com.google.gson.annotations.SerializedName
import com.myapp.myapplication.model.TVShow

data class TVShowResponse(
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val results: List<TVShow>
)