package com.myapp.myapplication.movie

data class Movie(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val posterPath: String,
    val releaseYear: String
) {
    val fullPosterPath: String
        get() = "$BASE_URL/w92/$posterPath"
}
