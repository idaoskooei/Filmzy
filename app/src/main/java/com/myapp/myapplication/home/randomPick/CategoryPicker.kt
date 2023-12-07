package com.myapp.myapplication.home.randomPick

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.CategoryList
import com.myapp.myapplication.composables.IntroText
import com.myapp.myapplication.home.categoryList.CategoryViewModel
import com.myapp.myapplication.navigation.Destinations

@Composable
fun CategoryPicker(
    viewModel: CategoryViewModel,
    navController: NavController
) {
    val state = viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            BackgroundImage(id = R.drawable.wheel)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                IntroText(text = "choose a category to see a random movie recommendation!")
                CategoryList(
                    onItemClick = { genreId -> navController.navigate("${Destinations.RANDOM_MOVIE_SCREEN}/$genreId") },
                    categories = state.value.genres,
                )
            }
        }
    }
}