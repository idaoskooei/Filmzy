package com.myapp.myapplication.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(icon: ImageVector, contentDescription: String, onClick: () -> Unit) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            tint = Color.DarkGray,
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(40.dp)
        )
    }
}