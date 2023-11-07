package com.myapp.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MoviesPage(navController: NavController) {
    Column {
        Text(text = "hello to your movies page")
    }
}