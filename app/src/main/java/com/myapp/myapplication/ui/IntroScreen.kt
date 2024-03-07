package com.myapp.myapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.myapplication.ui.composables.ActionButton
import com.myapp.myapplication.ui.composables.IntroBackgroundImage
import com.myapp.myapplication.ui.composables.IntroTextView

@Composable
fun IntroScreen(viewModel: IntroViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        IntroBackgroundImage()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IntroTextView(
                text = "Tired of", style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 33.sp,
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            IntroTextView(
                text = "Scrolling?", style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 33.sp,
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            IntroTextView(
                text = "Filmzy will help you pick something to watch!", style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 14.sp,
                )
            )
            Spacer(modifier = Modifier.height(70.dp))
            Row {
                ActionButton(text = "LET'S GO") {
                    viewModel.onProceed()
                }
            }
        }
    }
}