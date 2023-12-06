package com.myapp.myapplication.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.myapplication.model.Movie

@Composable
fun MovieItem(movie: Movie, onClick: (Movie) -> Unit, showImage: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick(movie) }
            .border(width = 2.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp)),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (showImage) {
                ImageView(movie = movie)
            }
            Spacer(modifier = Modifier.height(8.dp))
            TitleTextView(movie)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Overview: ${movie.overview}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
@Composable
fun TitleTextView(movie: Movie) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(movie.title)
            }
            append(" (${movie.releaseDate})")
        }, fontSize = 18.sp
    )
}

@Composable
@Preview(showBackground = true)
fun ItemPreview() {
    MovieItem(
        movie = Movie(
            overview = "epic love story",
            posterPath = "",
            releaseDate = "2023",
            title = "love story", id = 123,
            adult = true,
            duration = 120,
            genres = listOf(),
            website = "epiclovestory.com",
            liked = false
        ),
        onClick = {},
        showImage = true,
    )
}
