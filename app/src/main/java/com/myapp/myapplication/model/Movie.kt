package com.myapp.myapplication.model

import com.google.gson.annotations.SerializedName
import com.myapp.myapplication.BASE_IMAGE_URL
import com.myapp.myapplication.home.categoryList.TmdbGenre

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("genres") val genres: List<TmdbGenre>,
    @SerializedName("homepage") val website: String,
    @SerializedName("runtime") val duration: Int,
    val liked: Boolean
){
    val fullPosterPath: String
        get() = "$BASE_IMAGE_URL/$posterPath"
}