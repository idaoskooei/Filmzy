package com.myapp.myapplication.home.searchByTerm.shows

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.SearchTextField
import com.myapp.myapplication.composables.ShowItem
import com.myapp.myapplication.model.TVShow

@Composable
fun SearchShowsScreen(
    viewModel: SearchShowsViewModel = viewModel()
) {
    val shows by viewModel.uiState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            BackgroundImage(id = R.drawable.untitled_design)
            Column(Modifier.fillMaxWidth()) {
                SearchBar(viewModel)
                SearchResult(errorMessage, shows, onClick = {})
            }
        }
    }
}

@Composable
private fun SearchResult(
    errorMessage: String,
    shows: List<TVShow>,
    onClick: () -> Unit
) {
    if (errorMessage.isNotEmpty()) {
        Text(text = "Error: $errorMessage", style = TextStyle(color = Color.Black))
    } else {
        LazyColumn {
            items(shows) { show ->
                ShowItem(show = show, onClick = onClick, showImage = true)
            }
        }
    }
}

@Composable
private fun SearchBar(viewModel: SearchShowsViewModel) {
    var search by rememberSaveable { mutableStateOf("") }
    Row {
        SearchTextField(
            value = search,
            onValueChanged = { search = it },
            onDismissed = {},
            onSearchClick = { viewModel.onSearchClicked(search) },
        )
    }
}

@Composable
@Preview(showBackground = true)
fun searchShowsPreview() {
    SearchShowsScreen(
        viewModel = viewModel()
    )
}