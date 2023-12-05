package com.myapp.myapplication.home.searchByTerm.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.MovieItem
import com.myapp.myapplication.composables.SearchTextField
import com.myapp.myapplication.model.Movie

@Composable
fun SearchMoviesScreen(
    viewModel: SearchMoviesViewModel = viewModel()
) {
    val movies by rememberUpdatedState(newValue = viewModel.uiState.collectAsLazyPagingItems())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            BackgroundImage(id = R.drawable.untitled_design)
            Column(Modifier.fillMaxWidth()) {
                SearchBar(viewModel)
                SearchResult(movies, onClick = { viewModel.onMovieClicked(it) })
            }
        }
    }
}

@Composable
private fun SearchResult(
    movies: LazyPagingItems<Movie>,
    onClick: (Movie) -> Unit
) {
        LazyColumn {
            items(movies.itemCount) { index ->
                val movie = movies[index]
                if (movie != null) {
                    MovieItem(
                        movie = movie,
                        onClick = { onClick(movie) },
                        showImage = true
                    )
                }
            }
        }
}

@Composable
private fun SearchBar(viewModel: SearchMoviesViewModel) {
    var search by rememberSaveable { mutableStateOf("") }
    Row {
        SearchTextField(
            value = search,
            onValueChanged = { search = it },
            onDismissed = { },
            onSearchClick = { viewModel.onSearchClicked(search) },
            label = "Enter Movie Name"
        )
    }
}