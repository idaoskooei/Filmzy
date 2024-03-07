package com.myapp.myapplication.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    onDismissed: () -> Unit,
    onSearchClick: () -> Unit,
    label: String = ""
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        onDismissed()
                    }
                },
            value = TextFieldValue(value, TextRange(value.length)),
            onValueChange = { onValueChanged(it.text) },
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onSearchClick()
                keyboardController?.hide()

            }),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                cursorColor = Color.Black,
                textColor = Color.Black,
                focusedBorderColor = Color.Black,
                disabledBorderColor = Color.DarkGray
            ),
            trailingIcon = {
                IconButton(
                    onClick = { onSearchClick() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Red
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
                }
            })
    }
}
@Composable
@Preview(showBackground = true)
fun Preview() {
    SearchTextField(modifier = Modifier,
        label = "search",
        value = "hi",
        onSearchClick = {},
        onDismissed = {},
        onValueChanged = {})
}