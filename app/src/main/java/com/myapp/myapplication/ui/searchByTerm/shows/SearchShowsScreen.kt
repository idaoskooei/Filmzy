package com.myapp.myapplication.ui.searchByTerm.shows

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.myapp.myapplication.R
import com.myapp.myapplication.ui.composables.BackgroundImage
import com.myapp.myapplication.ui.composables.SearchTextField
import com.myapp.myapplication.ui.composables.ShowItem
import com.myapp.myapplication.model.TVShow

@Composable
fun SearchShowsScreen(
    viewModel: SearchShowsViewModel = viewModel()
) {
    val shows by rememberUpdatedState(newValue = viewModel.uiState.collectAsLazyPagingItems())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            BackgroundImage(id = R.drawable.untitled_design)
            Column(Modifier.fillMaxWidth()) {
                SearchBar(viewModel)
                SearchResult(
                    shows = shows,
                    onClick = { viewModel.onTvShowClicked(it) })
            }
        }
    }
}

@Composable
private fun SearchResult(
    shows: LazyPagingItems<TVShow>,
    onClick: (TVShow) -> Unit
) {
        LazyColumn {
            items(shows.itemCount) { index ->
                val show = shows[index]
                if (show != null) {
                    ShowItem(show = show, onClick = { onClick(show) }, showImage = true)
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
            label = "Enter Show Name"
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