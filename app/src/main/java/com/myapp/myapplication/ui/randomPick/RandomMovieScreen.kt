package com.myapp.myapplication.ui.randomPick

import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapp.myapplication.ui.composables.HorizontalDivider
import com.myapp.myapplication.ui.composables.ImageView
import com.myapp.myapplication.ui.composables.TextInfo

@Composable
fun RandomMovieScreen(vm: RandomMovieViewModel) {
    val state by vm.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        item {
            header(state)
            details(state)
        }
    }
}

@Composable
private fun details(state: RandomMovieViewModel.UiState) {
    TextInfo("Title: ${state.randomMovie?.title} ", Icons.Filled.Title)
    TextInfo("Release Date:   ${state.randomMovie?.title}", Icons.Filled.DateRange)
    TextInfo("Duration:   ${state.randomMovie?.title} minutes", Icons.Filled.Timer)
    TextInfo(
        "Genres:  ${state.randomMovie?.genres?.joinToString(", ") { genre -> genre.name }}",
        Icons.Filled.Category
    )
    TextInfo("Language:  ${state.randomMovie?.language}", Icons.Filled.Language)
    TextInfo(
        "Website: ${state.randomMovie?.website?.ifEmpty { "Not Available" }}",
        Icons.Filled.Info
    )
    TextInfo("Overview:   ${state.randomMovie?.overview}", Icons.Filled.Description)
}

@Composable
private fun header(state: RandomMovieViewModel.UiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        ImageView(posterPath = state.randomMovie?.fullPosterPath ?: "")
        Spacer(modifier = Modifier.padding(25.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(10.dp))
    }
}
