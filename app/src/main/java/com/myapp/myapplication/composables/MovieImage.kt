package com.myapp.myapplication.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.myapp.myapplication.R
import com.myapp.myapplication.movie.Movie

@Composable
fun MovieImage(movie: Movie) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = movie.fullPosterPath)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
            }).build()
    )
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ImageViewPreview() {
    MovieImage(
        movie = Movie(
            posterPath = "",
            id = 2,
            title = "love",
            releaseDate = "2023",
            overview = "love story"
        )
    )
}