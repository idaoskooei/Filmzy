package com.myapp.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.MyTopAppBar
import com.myapp.myapplication.ui.theme.blue

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        MyTopAppBar()
        Box {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                BackgroundImage(id = R.drawable.background_1)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 150.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        ChoicesButton(text = "PICK A CATEGORY")
                        ChoicesButton(text = "SPIN THE WHEEL")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ChoicesButton(text = "SEARCH")
                    }
                }

            }
        }
    }
}

@Composable
private fun ChoicesButton(text: String) {
    Button(
        onClick = {},
        modifier = Modifier.size(150.dp),
        colors = ButtonDefaults.buttonColors(containerColor = blue)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.Black,
                fontSize = 17.sp,
                fontFamily = FontFamily.Serif,
            )
        )
    }
}

@Preview(
    showBackground = true,
    device = "id:pixel_2"
)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

