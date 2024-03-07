package com.myapp.myapplication.ui.tvShow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.SkipNext
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
import com.myapp.myapplication.ui.composables.ActionButton
import com.myapp.myapplication.ui.composables.BackgroundImage
import com.myapp.myapplication.ui.composables.HorizontalDivider
import com.myapp.myapplication.ui.composables.ImageView
import com.myapp.myapplication.repo.TVShowResponse
import com.myapp.myapplication.model.Episode
import com.myapp.myapplication.model.Season

@Composable
fun ShowDetailScreen(viewModel: ShowDetailViewModel, navController: NavController) {
    val details by rememberUpdatedState(newValue = viewModel.uiState.collectAsState())
    ScreenContent(details, navController)
}

@Composable
private fun ScreenContent(details: State<TVShowResponse?>, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackgroundImage(id = R.drawable.untitled_design__19_)
        LazyColumn {
            item {
                ShowDetailHeader(details)
                ShowDetailsInfo(details, navController)
            }
        }
    }
}

@Composable
private fun ShowDetailHeader(details: State<TVShowResponse?>) {
    Column (modifier = Modifier.fillMaxWidth().padding(5.dp)){
        ImageView(posterPath = details.value?.fullPosterPath ?: "")
        Spacer(modifier = Modifier.padding(25.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(10.dp))
    }
}

@Composable
private fun ShowDetailsInfo(details: State<TVShowResponse?>, navController: NavController) {
    details.value?.let {
        TextInfo("Title:  ${it.name}", Icons.Filled.Title)
        TextInfo("First Air Date:  ${it.firstAirDate}", Icons.Filled.DateRange)
        TextInfo("Last Air Date:  ${it.lastAirDate}", Icons.Filled.DateRange)
        TextInfo(
            "Next Episode To Air:  ${it.nextEpisodeToAir?.name ?: "Not Available"}",
            Icons.Filled.DateRange
        )
        TextInfo("Status:  ${it.status}", Icons.Filled.SkipNext)
        TextInfo("Number of Episodes:  ${it.numberOfEpisodes}", Icons.Filled.FormatListNumbered)
        TextInfo("Number of Seasons: ${it.numberOfSeasons}", Icons.Filled.FormatListNumbered)
        TextInfo("Episode Run Time:  ${it.episodeRunTime} minutes", Icons.Filled.Timer)
        TextInfo(
            "Genres:  ${it.genres.joinToString(", ") { genre -> genre.name }}",
            Icons.Filled.Category
        )
        TextInfo("Languages: ${it.languages}", Icons.Filled.Language)
        TextInfo("Website: ${it.website?.ifEmpty { "Not Available" }}", Icons.Filled.Info)
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
    val sampleDetails = remember { mutableStateOf<TVShowResponse?>(generateSampleTvShowDetails()) }
    ScreenContent(details = sampleDetails, navController = navController)
}

private fun generateSampleTvShowDetails(): TVShowResponse {
    return TVShowResponse(
        genres = listOf(),
        website = "",
        overview = "",
        adult = true,
        results = listOf(),
        totalPages = 2,
        totalResults = 3,
        posterPath = "",
        id = 2,
        name = "love",
        episodeRunTime = listOf(3),
        firstAirDate = "",
        lastAirDate = "",
        lastEpisodeToAir = Episode(
            name = "",
            id = 2,
            overview = "",
            airDate = "",
            episodeNumber = 2,
            episodeType = "",
            runtime = 2,
            seasonNumber = 3
        ),
        nextEpisodeToAir = Episode(
            name = "",
            id = 2,
            overview = "",
            airDate = "",
            episodeNumber = 2,
            episodeType = "",
            runtime = 2,
            seasonNumber = 3
        ),
        numberOfEpisodes = 3,
        numberOfSeasons = 4,
        seasons = listOf(
            Season(
                seasonNumber = 2,
                airDate = "",
                overview = "",
                id = 2,
                name = "love",
                posterPath = "",
                episodeCount = 4
            )
        ),
        status = "finished",
        languages = listOf()
    )
}