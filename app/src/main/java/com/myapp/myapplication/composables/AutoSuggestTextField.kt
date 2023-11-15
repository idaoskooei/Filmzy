package com.myapp.myapplication.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties

@Composable
fun AutoSuggestTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    onItemClicked: (String) -> Unit,
    onDismissed: () -> Unit,
    onClearClick: () -> Unit,
    expanded: Boolean,
    list: List<String>,
    label: String = ""
) {

    val focusManager = LocalFocusManager.current
    Box(
        modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        OutlinedTextField(modifier = modifier
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
                focusManager.clearFocus()
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
                    onClick = { onClearClick() }, colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.Red
                    )
                ) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear")
                }
            })
        DropdownMenu(
            modifier = Modifier.padding(10.dp),
            expanded = expanded,
            properties = PopupProperties(
                focusable = false, dismissOnBackPress = true, dismissOnClickOutside = true
            ),
            onDismissRequest = onDismissed,
        ) {
            list.forEach { text ->
                DropdownMenuItem(onClick = {
                    onItemClicked(text)
                    focusManager.clearFocus()
                }) {
                    Text(text = text)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun Preview() {
    AutoSuggestTextField(modifier = Modifier,
        label = "search",
        value = "hi",
        expanded = true,
        list = emptyList(),
        onClearClick = {},
        onDismissed = {},
        onItemClicked = {},
        onValueChanged = {})
}