package com.myapp.myapplication.home.search

import com.myapp.myapplication.movie.Movie

data class MovieResponse(
    val results: List<Movie>
)