package com.myapp.myapplication.ui.movieDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Title
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myapp.myapplication.R
import com.myapp.myapplication.repo.search.MovieResponse
import com.myapp.myapplication.ui.composables.ActionButton
import com.myapp.myapplication.ui.composables.BackgroundImage
import com.myapp.myapplication.ui.composables.HorizontalDivider
import com.myapp.myapplication.ui.composables.ImageView
import com.myapp.myapplication.ui.composables.TextInfo

@Composable
fun MovieDetailsScreen(
    vm: MovieDetailsViewModel
) {
    val details by rememberUpdatedState(newValue = vm.uiState.collectAsState())
    ScreenContent(details) { vm.onBackButtonClicked() }
}

@Composable
fun ScreenContent(details: State<MovieResponse?>, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackgroundImage(id = R.drawable.untitled_design__19_)
        LazyColumn {
            item {
                MovieDetailsHeader(details)
                MovieDetails(details, onClick)
            }
        }
    }
}

@Composable
fun MovieDetailsHeader(details: State<MovieResponse?>) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        ImageView(posterPath = details.value?.fullPosterPath ?: "")
        Spacer(modifier = Modifier.padding(25.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
fun MovieDetails(details: State<MovieResponse?>, onClick: () -> Unit) {
    details.value?.let {
        TextInfo("Title:  ${it.title}", Icons.Filled.Title)
        TextInfo("Release Date:  ${it.releaseDate}", Icons.Filled.DateRange)
        TextInfo("Duration:  ${it.duration} minutes", Icons.Filled.Timer)
        TextInfo(
            "Genres:  ${it.genres.joinToString(", ") { genre -> genre.name }}",
            Icons.Filled.Category
        )
        TextInfo("Language:  ${it.language}", Icons.Filled.Language)
        TextInfo(
            "Website: ${it.website?.ifEmpty { "Not Available" }}",
            Icons.Filled.Info
        )
        TextInfo("Overview:   ${it.overview}", Icons.Filled.Description)
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        ActionButton(onClick = onClick, text = "Back")
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenContentPreview() {
    val sampleDetails = remember { mutableStateOf<MovieResponse?>(generateSampleMovieDetails()) }
    ScreenContent(details = sampleDetails) {}
}

private fun generateSampleMovieDetails(): MovieResponse {
    return MovieResponse(
        movieId = 1,
        duration = 200,
        genres = listOf(),
        website = "",
        overview = "",
        title = "",
        adult = true,
        releaseDate = "",
        poster = "",
        results = listOf(),
        totalPages = 2,
        totalResults = 3,
        language = "english"
    )
}