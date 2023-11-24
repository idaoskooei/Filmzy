package com.myapp.myapplication.home.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.ui.theme.customBackgroundColor


@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel,
    navController: NavController
) {
    val categories by viewModel.uiState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    DisposableEffect(Unit) {
        onDispose {}
    }
    MoviesList(navController, categories, errorMessage)
}

@Composable
private fun MoviesList(
    navController: NavController,
    categories: List<TmdbGenre>,
    errorMessage: String
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
            IntroText()
            CategoryList(
                onItemClick = { genre -> navController.navigate("movie_list_screen/{$genre}") },
                categories = categories,
                errorMessage = errorMessage
            )
        }
    }
}

@Composable
private fun CategoryList(
    categories: List<TmdbGenre>, onItemClick: (String) -> Unit, errorMessage: String
) {
    if (errorMessage.isNotEmpty()) {
        Text(text = "Error: $errorMessage", style = TextStyle(color = Color.Black))
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            items(categories) { category ->
                CategoryItem(category, onItemClick)
            }
        }
    }
}

@Composable
fun CategoryItem(category: TmdbGenre, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onItemClick(category.name) },
        colors = CardDefaults.cardColors(
            containerColor = customBackgroundColor
        )
    ) {
        Text(
            text = category.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp,
                color = Color.Black
            )
        )
    }
}

@Composable
private fun IntroText() {
    Text(
        text = "Choose a category to see movie recommendations!",
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .wrapContentSize(Alignment.Center),
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            color = Color.Black
        )
    )
}

@Composable
@Preview(showBackground = true)
fun Preview() {
    val navController = rememberNavController()
    MoviesList(
        navController = navController,
        errorMessage = "",
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