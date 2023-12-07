package com.myapp.myapplication.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.ActionButton
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.HorizontalDivider
import com.myapp.myapplication.composables.ImageView
import com.myapp.myapplication.home.searchByTerm.movies.MovieResponse

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel, navController: NavController) {
    val details by rememberUpdatedState(newValue = viewModel.uiState.collectAsState())
    ScreenContent(details, navController)
}

@Composable
fun ScreenContent(details: State<MovieResponse?>, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        BackgroundImage(id = R.drawable.untitled_design__19_)
        LazyColumn {
            item {
                MovieDetailsHeader(details)
                MovieDetailsInfo(details, navController)
            }
        }
    }
}

@Composable
fun MovieDetailsHeader(details: State<MovieResponse?>) {
    ImageView(posterPath = details.value?.fullPosterPath ?: "")
    Spacer(modifier = Modifier.padding(25.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.padding(10.dp))
}

@Composable
fun MovieDetailsInfo(details: State<MovieResponse?>, navController: NavController) {
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
        ActionButton(onClick = { navController.popBackStack() }, text = "Back")
    }
}

@Composable
fun TextInfo(text: String, imageVector: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        CustomIcon(imageVector)
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            fontSize = 16.sp,
            text = text,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun CustomIcon(imageVector: ImageVector) {
    Card(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Icon(
            tint = Color.Black,
            imageVector = imageVector,
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenContentPreview() {
    val navController = rememberNavController()
    val sampleDetails = remember { mutableStateOf<MovieResponse?>(generateSampleMovieDetails()) }
    ScreenContent(details = sampleDetails, navController = navController)
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