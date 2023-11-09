package com.myapp.myapplication.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

@Composable
fun IntroTextView(
    text: String,
    style: TextStyle = MaterialTheme.typography.h1
) {
    Text(text = text, style = style)
}
