package com.myapp.myapplication.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun MyTopAppBar() {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.Transparent,
        contentPadding = PaddingValues(7.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyIconButton(icon = Icons.Filled.Home, contentDescription = "home") {}
            MyIconButton(icon = Icons.Filled.Person, contentDescription = "profile") {}
        }
    }
}

@Composable
fun MyIconButton(icon: ImageVector, contentDescription: String, onClick: () -> Unit) {
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

@Composable
@Preview(device = "id:pixel_4", showBackground = true)
fun appBarPreview() {
    MyTopAppBar()
}