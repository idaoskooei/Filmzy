package com.myapp.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun IntroScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the App")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("sign_in_screen")
            }
        ) {
            Text("Log In")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Navigate to the sign-up screen
                navController.navigate("sign_up_screen")
            }
        ) {
            Text("Sign Up")
        }
    }
}
