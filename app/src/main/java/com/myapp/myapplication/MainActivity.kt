package com.myapp.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.auth.model.AuthRepository
import com.myapp.myapplication.auth.signin.SignInScreen
import com.myapp.myapplication.auth.signin.SignInViewModel
import com.myapp.myapplication.auth.signup.SignUpScreen
import com.myapp.myapplication.auth.signup.SignUpViewModel
import com.myapp.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
    val authRepository = AuthRepository()

    val startDestination = if (authRepository.isUserSignedIn()) {
        "home_screen"
    } else {
        "intro_screen"
    }

    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable("intro_screen") {
            IntroScreen(navController = navController)
        }

        composable("sign_in_screen") {
            SignInScreen(

                viewModel = SignInViewModel(
                    authRepository = AuthRepository(),
                    navController = navController
                )
            )
        }

        composable("sign_up_screen") {
            SignUpScreen(
                navController = navController,
                viewModel = SignUpViewModel(
                    authRepository = AuthRepository(),
                    navController = navController
                )
            )
        }

        composable("home_screen") {
            HomeScreen()
        }
    }
}
