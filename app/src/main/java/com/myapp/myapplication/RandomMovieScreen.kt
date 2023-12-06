package com.myapp.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Random Movie:")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "title: ${state.randomMovie?.title}")
            Spacer(modifier = Modifier.height(16.dp))
            state.randomMovie?.let { ImageView(movie = it) }
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(onClick = { navController.popBackStack() }, text = "Back")
        }
    }

    LaunchedEffect(categoryId) {
        viewModel.pickRandomMovie(categoryId)
    }
}
