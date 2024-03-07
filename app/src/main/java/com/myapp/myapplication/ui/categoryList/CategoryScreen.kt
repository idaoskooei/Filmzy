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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myapp.myapplication.R
import com.myapp.myapplication.repo.category.TmdbGenre
import com.myapp.myapplication.ui.composables.BackgroundImage
import com.myapp.myapplication.ui.composables.CategoryList
import com.myapp.myapplication.ui.composables.IntroText


@Composable
fun CategoryScreen(
    vm: CategoryViewModel,
) {
    val state = vm.uiState.collectAsState()

    MoviesList(categories = state.value.genres) { genreId -> vm.onCategoryClicked(genreId) }
}
@Composable
private fun MoviesList(
    categories: List<TmdbGenre>,
    onItemClick: (genreId: Int) -> Unit
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
                onItemClick = onItemClick,
                categories = categories,
            )
        }
    }
}




@Composable
@Preview(showBackground = true)
fun Preview() {
    MoviesList(
        categories = listOf(
            TmdbGenre(1, "comedy"),
            TmdbGenre(3, "romance"),
            TmdbGenre(3, "action"),
            TmdbGenre(3, "adventure"),
            TmdbGenre(3, "drama"),
            TmdbGenre(3, "horror")
        )
    ) {}
}