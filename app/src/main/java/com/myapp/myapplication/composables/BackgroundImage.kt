package com.myapp.myapplication.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.myapp.myapplication.R

@Composable
 fun BackgroundImage() {
    val backgroundPainter: Painter = painterResource(id = R.drawable.untitled_design)
    Image(
        painter = backgroundPainter,
        contentDescription = null,  // Set content description if needed
        modifier = Modifier.fillMaxSize(),  // Fills the entire screen
        contentScale = ContentScale.Crop
    )
}