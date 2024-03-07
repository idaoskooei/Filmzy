package com.myapp.myapplication.ui.categoryList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.R
import com.myapp.myapplication.repo.category.TmdbGenre
import com.myapp.myapplication.ui.composables.BackgroundImage
import com.myapp.myapplication.ui.composables.CategoryList
import com.myapp.myapplication.ui.composables.IntroText


@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel,
    navController: NavController
) {
    val state = viewModel.uiState.collectAsState()

    MoviesList(navController = navController, categories = state.value.genres)
}
@Composable
private fun MoviesList(
    navController: NavController,
    categories: List<TmdbGenre>,
) {
    Box(
        Modifier
            .fillMaxSize()
    ) {
        BackgroundImage(id = R.drawable.untitled_design__14_)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            IntroText(text = "Choose a category to see movie recommendations!")
            CategoryList(
                onItemClick = { genreId -> navController.navigate("movie_list_screen/$genreId") },
                categories = categories,
            )
        }
    }
}





@Composable
@Preview(showBackground = true)
fun Preview() {
    val navController = rememberNavController()
    MoviesList(
        navController = navController,
        categories = listOf(
            TmdbGenre(1, "comedy"),
            TmdbGenre(3, "romance"),
            TmdbGenre(3, "action"),
            TmdbGenre(3, "adventure"),
            TmdbGenre(3, "drama"),
            TmdbGenre(3, "horror")
        )
    )
}