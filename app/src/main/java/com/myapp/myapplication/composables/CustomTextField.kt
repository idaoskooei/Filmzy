package com.myapp.myapplication.composables

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit = {},
    onClicked: () -> Unit = {},
    maxLine: Int = 2,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val textFieldValueFun = { textValue: TextFieldValue ->
        onValueChange(textValue.text)
    }
    OutlinedTextField(
        value = TextFieldValue(
            value,
            selection = TextRange(value.length),
        ),
        onValueChange = textFieldValueFun,
        modifier = modifier.padding(10.dp),
        maxLines = maxLine,
        label = { Text(label) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.LightGray,
            cursorColor = Color.Black
        ),
        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect {
                    if (it is PressInteraction.Release) {
                        onClicked()
                    }
                }
            }
        },
        enabled = enabled
    )
}