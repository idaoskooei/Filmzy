package com.myapp.myapplication.home.searchByTerm.movies

import com.myapp.myapplication.model.Movie

data class MovieResponse(
    val results: List<Movie>
)