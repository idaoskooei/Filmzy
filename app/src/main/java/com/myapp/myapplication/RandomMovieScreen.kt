package com.myapp.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapp.myapplication.composables.ActionButton
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.ImageView
import com.myapp.myapplication.movie.TextInfo

@Composable
fun RandomMovieScreen(
    viewModel: RandomMovieViewModel,
    categoryId: Int,
    navController: NavController,
) {
    val state by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage(id = R.drawable.wheel)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenContent(state, navController)
        }
    }

    LaunchedEffect(categoryId) {
        viewModel.pickRandomMovie(categoryId)
    }
}
@Composable
private fun ScreenContent(
    state: RandomMovieViewModel.UiState,
    navController: NavController
) {
    Spacer(modifier = Modifier.padding(25.dp))
    Text("Random Movie:")
    Spacer(modifier = Modifier.padding(25.dp))
    ImageView(posterPath = state.randomMovie?.fullPosterPath ?: "")
    Spacer(modifier = Modifier.padding(25.dp))
    state.randomMovie?.let {
        TextInfo("Title:  ${it.title}", Icons.Filled.Title)
        TextInfo("Release Date:  ${it.releaseDate}", Icons.Filled.DateRange)
//        TextInfo("Duration:  ${it.duration} minutes", Icons.Filled.Timer)
        TextInfo("Overview:   ${it.overview}", Icons.Filled.Description)
    }
    Spacer(modifier = Modifier.height(16.dp))
    ActionButton(onClick = { navController.popBackStack() }, text = "Back")
}
