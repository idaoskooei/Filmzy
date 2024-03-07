package com.myapp.myapplication.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.myapplication.ui.categoryList.TmdbGenre
import com.myapp.myapplication.ui.theme.customBackgroundColor

@Composable
fun CategoryList(
    categories: List<TmdbGenre>, onItemClick: (Int) -> Unit,
) {
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

@Composable
fun CategoryItem(category: TmdbGenre, onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onItemClick(category.id) },
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
@Preview(showBackground = true)
fun CategoryListPreview() {
    CategoryList(categories = listOf(element = TmdbGenre(2, "drama")), onItemClick = {})
}