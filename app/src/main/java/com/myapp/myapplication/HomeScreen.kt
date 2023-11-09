package com.myapp.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.MovieItem
import com.myapp.myapplication.movie.Movie

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BackgroundImage()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            MovieItem(
                movie = Movie(
                    1234,
                    "notebook",
                    overview = "good movie",
                    posterPath = "image",
                    releaseDate = "2023"
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_2"
)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen()
}

