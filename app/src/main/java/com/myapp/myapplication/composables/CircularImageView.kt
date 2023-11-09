package com.myapp.myapplication.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.myapp.myapplication.R

@Composable
fun CircularImageView(imageUri: String?, size: Dp, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(40.dp))
            .border(2.dp, Color.Gray, RoundedCornerShape(40.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        val painter = if (imageUri.isNullOrEmpty()) {
            painterResource(id = R.drawable.logo)
        } else {
            rememberAsyncImagePainter(model = imageUri)
        }
        Image(
            contentScale = ContentScale.Crop,
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .size(size)
                .background(Color.White, RoundedCornerShape(40.dp))
                .clickable { onClick() }
        )
    }
}

@Composable
@Preview(showBackground = true, device = "id:pixel_4")
fun ImagePreview() {
    CircularImageView(imageUri = "", size = 200.dp) {
    }
}