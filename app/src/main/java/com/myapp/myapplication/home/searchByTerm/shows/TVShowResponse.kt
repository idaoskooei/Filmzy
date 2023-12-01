package com.myapp.myapplication.home.searchByTerm.shows

import com.google.gson.annotations.SerializedName
import com.myapp.myapplication.BASE_IMAGE_URL
import com.myapp.myapplication.home.categoryList.TmdbGenre
import com.myapp.myapplication.model.Episode
import com.myapp.myapplication.model.Season
import com.myapp.myapplication.model.TVShow

data class TVShowResponse(
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("results") val results: List<TVShow>,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("episode_run_time") val episodeRunTime: List<Int>?,
    @SerializedName("first_air_date") val firstAirDate: String,
    @SerializedName("genres") val genres: List<TmdbGenre>,
    @SerializedName("homepage") val website: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("last_air_date") val lastAirDate: String,
    @SerializedName("last_episode_to_air") val lastEpisodeToAir: Episode?,
    @SerializedName("name") val name: String,
    @SerializedName("next_episode_to_air") val nextEpisodeToAir: Episode?,
    @SerializedName("number_of_episodes") val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons") val numberOfSeasons: Int,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("seasons") val seasons: List<Season>,
    @SerializedName("status") val status: String,
    @SerializedName("languages") val languages: List<String>,
) {
    val fullPosterPath: String
        get() = "$BASE_IMAGE_URL/$posterPath"
}




