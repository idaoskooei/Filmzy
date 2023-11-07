package com.myapp.myapplication.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.AuthButton
import com.myapp.myapplication.composables.EmailTextField
import com.myapp.myapplication.composables.PasswordTextField

@Composable
fun SignUpScreen(
    navController: NavController,
    onSignUpButtonClicked: (email: String, password: String, confirmPassword: String) -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
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
            onClick = { onSignUpButtonClicked(email, password, confirmPassword) }
        )
    }
}
