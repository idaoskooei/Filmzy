package com.myapp.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.R
import com.myapp.myapplication.ui.composables.BackgroundImage
import com.myapp.myapplication.ui.theme.blue

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box {
            BackGroundImage()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 150.dp)
                ) {
                    CategoryButtons(viewModel)
                    Spacer(modifier = Modifier.padding(10.dp))
                    SearchButtons(viewModel)
                }
            }
        }
    }
}

@Composable
private fun SearchButtons(viewModel: HomeViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ChoicesButton(
            text = "SEARCH MOVIES",
            onClick = { viewModel.onSearchMoviesButtonClicked() })
        ChoicesButton(
            text = "SEARCH SHOWS",
            onClick = { viewModel.onSearchShowsButtonClicked() })
    }
}

@Composable
private fun CategoryButtons(viewModel: HomeViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ChoicesButton(
            text = "PICK A CATEGORY",
            onClick = { viewModel.onCategoryButtonClicked() }
        )
        ChoicesButton(
            text = "PICK A RANDOM MOVIE",
            onClick = { viewModel.onPickRandomButtonClicked() })
    }
}

@Composable
private fun BackGroundImage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        BackgroundImage(id = R.drawable.background_1)
    }
}

@Composable
private fun ChoicesButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(150.dp),
        colors = ButtonDefaults.buttonColors(containerColor = blue)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.Black,
                fontSize = 17.sp,
                fontFamily = FontFamily.Serif,
            )
        )
    }
}
@Preview(
    showBackground = true,
    device = "id:pixel_2"
)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(
        viewModel = HomeViewModel(navController)
    )
}

