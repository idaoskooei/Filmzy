package com.myapp.myapplication.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.AutoSuggestTextField
import com.myapp.myapplication.composables.BackgroundImage

@Composable
fun SearchScreen(
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        var search by rememberSaveable { mutableStateOf("") }


        AutoSuggestTextField(
            value = search,
            onValueChanged = { search = it },
            onItemClicked = {},
            onDismissed = {},
            onClearClick = { search = "" },
            expanded = false,
            list = emptyList()
        )
        BackgroundImage(id = R.drawable.untitled_design)
    }
}

@Composable
@Preview
fun SearchScreenPreview() {
    SearchScreen()
}