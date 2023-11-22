package com.myapp.myapplication.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.myapp.myapplication.R
import com.myapp.myapplication.movie.Movie


@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 2.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp)),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(movie.title)
                            }
                            append(" (${movie.releaseYear})")
                        },
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Overview: ${movie.overview}")
                    MovieImage(movie)
                }
            }
        }
    }
}

@Composable
private fun MovieImage(movie: Movie) {
    val painter = rememberImagePainter(
        data = movie.fullPosterPath,
        builder = {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
        }
    )
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .width(200.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
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
            title = "love story",
            id = 123,
            releaseYear = "2023"
        )
    )
}
