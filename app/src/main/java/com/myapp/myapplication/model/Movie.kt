package com.myapp.myapplication.model

import com.google.gson.annotations.SerializedName
import com.myapp.myapplication.BASE_IMAGE_URL

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