package com.myapp.myapplication.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.ActionButton
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.CircularImageView
import com.myapp.myapplication.composables.HorizontalDivider
import com.myapp.myapplication.composables.IconButton
import com.myapp.myapplication.composables.IconButtonWithText

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {

    val displayName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            BackgroundImage(id = R.drawable.untitled_design)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                NavigationButton(viewModel)
                ProfileImage()
                UserDetail(displayName = displayName)
                HorizontalDivider()
                ProfileContents(onFavoriteEventLayoutClicked = { /*TODO*/ }) {}
                SignOutButton()
            }
        }
    }
}

@Composable
private fun SignOutButton() {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        ActionButton(text = "SIGN OUT") {}
    }
}

@Composable
private fun NavigationButton(viewModel: ProfileViewModel) {
    IconButton(icon = Icons.Filled.Home, contentDescription = "home") {
        viewModel.onProfileButtonClicked()
    }
}

@Composable
private fun ProfileImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularImageView(imageUri = "", size = 200.dp) {}
    }
}

@Composable
fun UserDetail(displayName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = displayName,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
private fun ProfileContents(
    onFavoriteEventLayoutClicked: () -> Unit, onPersonalDataLayoutClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        IconButtonWithText(
            icon = Icons.Filled.Favorite, text = "MY FAVORITE MOVIES"
        ) {
            onFavoriteEventLayoutClicked()
        }
        IconButtonWithText(
            icon = Icons.Filled.Person2, text = "EDIT MY PROFILE"
        ) {
            onPersonalDataLayoutClicked()
        }

        IconButtonWithText(
            icon = Icons.Filled.NotificationsActive, text = "NOTIFICATIONS"
        ) {}
    }
}

@Composable
@Preview(device = "id:pixel_4", showBackground = true)
fun ProfilePreview() {
    val navController = rememberNavController()
    ProfileScreen(viewModel = ProfileViewModel(navController))
}