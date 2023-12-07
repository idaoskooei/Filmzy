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
import androidx.compose.material.icons.filled.Home
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
import com.myapp.myapplication.auth.model.AuthRepository
import com.myapp.myapplication.composables.ActionButton
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.HorizontalDivider
import com.myapp.myapplication.composables.IconButton
import com.myapp.myapplication.composables.IconButtonWithText
import com.myapp.myapplication.composables.ProfileImage

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
                ProfileContents {
                    viewModel.onTestClicked()
                }
                SignOutButton(onSignOut = { viewModel.onSignOutButtonClicked() })
            }
        }
    }
}

@Composable
private fun SignOutButton(onSignOut: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        ActionButton(text = "SIGN OUT") {
            onSignOut()
        }
    }
}

@Composable
private fun NavigationButton(viewModel: ProfileViewModel) {
    IconButton(icon = Icons.Filled.Home, contentDescription = "home") {
        viewModel.onProfileButtonClicked()
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
    onPersonalDataLayoutClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        IconButtonWithText(
            icon = Icons.Filled.Person2, text = "EDIT MY PROFILE"
        ) {
            onPersonalDataLayoutClicked()
        }
    }
}

@Composable
@Preview(device = "id:pixel_4", showBackground = true)
fun ProfilePreview() {
    val navController = rememberNavController()
    val authRepository = AuthRepository()
    ProfileScreen(
        viewModel = ProfileViewModel(
            navController = navController,
            authRepository = authRepository
        )
    )
}