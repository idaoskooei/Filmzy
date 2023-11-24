package com.myapp.myapplication.home.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.MovieItem
import com.myapp.myapplication.movie.Movie

@Composable
fun MovieListScreen(
    movies: List<Movie>, onMovieClick: (Movie) -> Unit
) {
    Box {
        BackgroundImage(id = R.drawable.untitled_design)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            LazyColumn {
                items(movies) { movie ->
                    MovieItem(movie = movie, onClick = { onMovieClick(movie) }, showImage = false)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MovieListPreview() {
    MovieListScreen(movies = listOf(
        Movie(
            overview = "love story",
            releaseDate = "2023",
            title = "love",
            id = 12,
            posterPath = ""
        ), Movie(
            overview = "love story",
            releaseDate = "2023",
            title = "love",
            id = 12,
            posterPath = ""
        ), Movie(
            overview = "love story",
            releaseDate = "2023",
            title = "love",
            id = 12,
            posterPath = ""
        )
    ), onMovieClick = {})
}