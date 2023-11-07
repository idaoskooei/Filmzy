package com.myapp.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.signin.SignInScreen
import com.myapp.myapplication.signin.SignInViewModel
import com.myapp.myapplication.signup.SignUpScreen
import com.myapp.myapplication.signup.SignUpViewModel
import com.myapp.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private lateinit var signInViewModel: SignInViewModel
    private lateinit var signUpViewModel: SignUpViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInViewModel = ViewModelProvider(this)[SignInViewModel::class.java]
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()

                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = "intro_screen"
    ) {
        composable("intro_screen") {
            IntroScreen(navController = navController)
        }

        composable("sign_in_screen") {
            SignInScreen(
                navController = navController,
                onSignInButtonClicked = { _, _ -> run {} }
            )
        }

        composable("sign_up_screen") {
            SignUpScreen(
                navController = navController,
                onSignUpButtonClicked = { _, _, _ -> run {} })
        }

    }
}

