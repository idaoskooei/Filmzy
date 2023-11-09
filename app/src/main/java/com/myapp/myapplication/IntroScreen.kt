package com.myapp.myapplication

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
import androidx.navigation.NavController
import com.myapp.myapplication.composables.ActionButton
import com.myapp.myapplication.composables.IntroBackgroundImage
import com.myapp.myapplication.composables.IntroTextView

@Composable
fun IntroScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        IntroBackgroundImage()
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IntroTextView(
                text = "Tired of scrolling?", style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 30.sp,
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

            IntroTextView(
                text = "Filmzy will help you pick a movie to watch!", style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontSize = 15.sp,
                )
            )
            Spacer(modifier = Modifier.height(70.dp))

            IntroButtons(navController)

        }
    }

}

@Composable
private fun IntroButtons(navController: NavController) {
    Row {
        ActionButton(text = "Sign In") {
            navController.navigate("sign_in_screen")
        }

        ActionButton(text = "Sign Up") {
            navController.navigate("sign_up_screen")
        }
    }
}
