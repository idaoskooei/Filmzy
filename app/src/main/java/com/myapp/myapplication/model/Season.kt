package com.myapp.myapplication.model

import com.google.gson.annotations.SerializedName
import com.myapp.myapplication.BASE_IMAGE_URL

data class Season(
    @SerializedName("air_date") val airDate: String,
    @SerializedName("episode_count") val episodeCount: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("season_number") val seasonNumber: Int
) {
    val fullPosterPath: String
        get() = "$BASE_IMAGE_URL/$posterPath"
}
