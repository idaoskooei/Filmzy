package com.myapp.myapplication.home.searchByCategory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.MovieItem
import com.myapp.myapplication.model.Movie

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel
) {
    val movies by rememberUpdatedState(newValue = viewModel.uiState.collectAsLazyPagingItems())

    MoviesList(movies = movies, onMovieClick = { viewModel.onMovieClicked(it) })
}

@Composable
private fun MoviesList(
    movies: LazyPagingItems<Movie>, onMovieClick: (Movie) -> Unit
) {
    Box {
        BackgroundImage(id = R.drawable.untitled_design)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            LazyColumn {
                items(movies.itemCount) { index ->
                    val movie = movies[index]
                    if (movie != null) {
                        MovieItem(
                            movie = movie,
                            onClick = { onMovieClick(movie) },
                            showImage = false
                        )
                    }
                }
            }
        }
    }
}