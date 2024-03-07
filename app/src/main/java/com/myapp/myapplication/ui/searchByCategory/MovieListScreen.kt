package com.myapp.myapplication.ui.searchByCategory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.myapp.myapplication.R
import com.myapp.myapplication.ui.composables.BackgroundImage
import com.myapp.myapplication.ui.composables.IconButton
import com.myapp.myapplication.ui.composables.MovieItem
import com.myapp.myapplication.model.Movie

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel
) {
    val movies by rememberUpdatedState(newValue = viewModel.uiState.collectAsLazyPagingItems())

    MoviesList(
        movies = movies,
        onMovieClick = { viewModel.onMovieClicked(it) },
        onHomeClick = { viewModel.onHomeButtonClicked() },
        onBackClick = { viewModel.onBackButtonClicked() }
    )
}

@Composable
private fun MoviesList(
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Movie) -> Unit,
    onHomeClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box {
        BackgroundImage(id = R.drawable.untitled_design)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            TopBarButtons(onBackClick, onHomeClick)
            LazyColumn {
                items(movies.itemCount) { index ->
                    val movie = movies[index]
                    if (movie != null) {
                        MovieItem(
                            movie = movie,
                            onClick = { onMovieClick(movie) },
                            showImage = false,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TopBarButtons(onBackClick: () -> Unit, onHomeClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        IconButton(icon = Icons.Filled.ArrowBack, contentDescription = "back") {
            onBackClick()
        }
        Spacer(modifier = Modifier.padding(5.dp))
        IconButton(icon = Icons.Filled.Home, contentDescription = "home") {
            onHomeClick()
        }
    }
}