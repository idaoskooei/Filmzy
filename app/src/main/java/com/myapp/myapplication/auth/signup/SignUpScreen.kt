package com.myapp.myapplication.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.AuthButton
import com.myapp.myapplication.composables.EmailTextField
import com.myapp.myapplication.composables.ErrorTextView
import com.myapp.myapplication.composables.PasswordTextField
import com.myapp.myapplication.auth.model.AuthRepository

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val painter = painterResource(id = R.drawable.logo)
        Image(painter = painter, contentDescription = "welcome back logo")

        EmailTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )

        PasswordTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password"
        )

        PasswordTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password"
        )

        AuthButton(
            modifier = Modifier.padding(top = 30.dp, bottom = 30.dp),
            text = "SIGN UP",
            onClick = { viewModel.onSignUpButtonClicked(email, password, confirmPassword) },
        )

        if (uiState.isInProgress) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = Color.Blue
            )
        }

        if (uiState.errorMessage != null) {
            ErrorTextView(
                error = uiState.errorMessage!!
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = "id:pixel_2")
fun SignUpScreenPreview() {
    val navController = rememberNavController()

    SignUpScreen(
        navController = navController, viewModel = SignUpViewModel(
            authRepository = AuthRepository(), navController
        )
    )
}
