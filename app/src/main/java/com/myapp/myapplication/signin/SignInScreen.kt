package com.myapp.myapplication.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.AuthButton
import com.myapp.myapplication.composables.EmailTextField
import com.myapp.myapplication.composables.PasswordTextField
import com.myapp.myapplication.model.AuthRepository

@Composable
fun SignInScreen(
    navController: NavController, viewModel: SignInViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val painter = painterResource(id = R.drawable.welcomeback)
        Image(painter = painter, contentDescription = "welcome back logo")

        EmailTextField(
            value = email, onValueChange = { email = it }, label = "Email"
        )

        PasswordTextField(
            value = password, onValueChange = { password = it }, label = "Password"
        )
        AuthButton(
            modifier = Modifier.padding(top = 30.dp, bottom = 30.dp),
            text = "SIGN IN",
            onClick = {
                viewModel.onSignInButtonClicked(email, password)
            })
    }
}

@Preview(device = "id:pixel_2", showBackground = true)
@Composable
fun SignInScreenPreview() {
    val navController = rememberNavController()

    SignInScreen(
        navController = navController, viewModel = SignInViewModel(
            authRepository = AuthRepository(),navController
        )
    )
}