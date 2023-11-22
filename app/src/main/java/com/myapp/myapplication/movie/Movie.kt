package com.myapp.myapplication.movie

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
){
    val fullPosterPath: String
        get() = "$BASE_IMAGE_URL/$posterPath"
}