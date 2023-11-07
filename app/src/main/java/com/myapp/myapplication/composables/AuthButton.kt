package com.myapp.myapplication.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.myapplication.ui.theme.Red

@Composable
@Preview
fun AuthButtonPreview() {
    AuthButton(text = "Button", onClick = { /*TODO*/ })
}

@Composable
fun AuthButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Red
    )
) {
    Button(
        shape = RoundedCornerShape(20.dp),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = colors
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Text(
                text,
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 17.sp)
            )
        }
    }
}