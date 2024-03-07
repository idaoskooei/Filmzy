package com.myapp.myapplication.repo.search

import com.google.gson.annotations.SerializedName
import com.myapp.myapplication.BASE_IMAGE_URL
import com.myapp.myapplication.repo.category.TmdbGenre
import com.myapp.myapplication.model.Movie

data class MovieResponse(
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("id") val movieId: Int,
    @SerializedName("results") val results: List<Movie>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("homepage") val website: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val poster: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("runtime") val duration: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("genres") val genres: List<TmdbGenre>,
    @SerializedName("original_language") val language: String?
) {
    val fullPosterPath: String
        get() = "$BASE_IMAGE_URL/$poster"
}