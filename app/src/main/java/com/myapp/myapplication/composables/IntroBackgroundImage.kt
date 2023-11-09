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
fun IntroBackgroundImage() {
    val backgroundPainter: Painter = painterResource(id = R.drawable.untitled_design__3_)
    Image(
        painter = backgroundPainter,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}