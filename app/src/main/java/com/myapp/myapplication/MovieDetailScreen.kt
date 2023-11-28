package com.myapp.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.NoAdultContent
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.HorizontalDivider
import com.myapp.myapplication.composables.ImageView
import com.myapp.myapplication.home.categoryList.TmdbGenre
import com.myapp.myapplication.model.Movie

@Composable
fun MovieDetailScreen(movie: Movie) {
    Box {
        BackgroundImage(id = R.drawable.untitled_design__19_)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
        {
            ImageView(movie = movie)
            HorizontalDivider()
            Spacer(modifier = Modifier.padding(10.dp))
            TextInfo(text = "Title:  ${movie.title ?: ""}", imageVector = Icons.Filled.Title)
            TextInfo(
                text = "Release Date: ${movie.releaseDate ?: ""}",
                imageVector = Icons.Filled.DateRange
            )
            TextInfo(text = "Duration: ${movie.duration} minutes", imageVector = Icons.Filled.Timer)
            TextInfo(
                text = "Adult: ${if (movie.adult) "Yes" else "No"}",
                imageVector = Icons.Filled.NoAdultContent
            )
            TextInfo(
                text = "Genres: ${movie.genres.joinToString(", ") { it.name }}",
                imageVector = Icons.Filled.Category
            )
            TextInfo(
                text = "Website: ${movie.website}",
                imageVector = Icons.Filled.Info
            )
            TextInfo(
                text = "Overview:  ${movie.overview ?: ""}",
                imageVector = Icons.Filled.Description
            )
        }
    }
}

@Composable
fun TextInfo(text: String, imageVector: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        CustomIcon(imageVector)
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            fontSize = 16.sp,
            text = text,
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.SemiBold
        )
    }
}


@Composable
private fun CustomIcon(imageVector: ImageVector) {
    Card(
        modifier = Modifier.padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Icon(
            tint = Color.Black,
            imageVector = imageVector,
            contentDescription = ""
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MovieDetailPreview() {
    MovieDetailScreen(
        Movie(
            adult = true,
            posterPath = "",
            title = "what if",
            id = 2,
            releaseDate = "2023",
            overview = "story about love tha starts in a foreign city between two young students who have moved from paris.",
            duration = 120,
            genres = listOf(TmdbGenre(1, "comedy")),
            website = "whatif.com"
        )
    )
}