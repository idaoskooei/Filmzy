package com.myapp.myapplication.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.myapplication.model.TVShow

@Composable
fun ShowItem(show: TVShow, onClick: (TVShow) -> Unit, showImage: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick(show) }
            .border(width = 2.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp)),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (showImage) {
                ImageView(show = show)
            }
            Spacer(modifier = Modifier.height(8.dp))
            TitleTextView(show)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Overview: ${show.overview}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TitleTextView(show: TVShow) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(show.name)
            }
            append(" (${show.airDate})")
        }, fontSize = 18.sp
    )
}

@Preview(showBackground = true)
@Composable
fun ShowItemPrev() {
    ShowItem(
        show = TVShow(1, "prison break", "escape", "", "english", "2023-01-20", true),
        onClick = { /*TODO*/ },
        showImage = true
    )
}