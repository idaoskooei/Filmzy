package com.myapp.myapplication.model

import com.google.gson.annotations.SerializedName
import com.myapp.myapplication.BASE_IMAGE_URL

data class TVShow(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("first_air_date") val airDate: String?,
    @SerializedName("adult") val adult: Boolean,
) {
    val fullPosterPath: String
        get() = "$BASE_IMAGE_URL/$posterPath"
}
