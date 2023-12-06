package com.myapp.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.CategoryList
import com.myapp.myapplication.composables.IntroText
import com.myapp.myapplication.home.categoryList.CategoryViewModel

@Composable
fun CategoryPicker(viewModel: CategoryViewModel) {

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
                IntroText(text = "choose a category and spin the wheel!")
                CategoryList(
                    onItemClick = {},
                    categories = state.value.genres,
                )
            }
        }
    }
}