package com.myapp.myapplication.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.myapp.myapplication.model.Movie

@Composable
fun FavoriteIcon(movie: Movie, onFavClick: (Movie) -> Unit) {
    androidx.compose.material3.IconButton(
        onClick = { onFavClick(movie) },
        colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Red)
    ) {
        Icon(
            painter = rememberVectorPainter(
                if (movie.liked) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Filled.FavoriteBorder
                }
            ),
            contentDescription = "thumb",
        )
    }
}