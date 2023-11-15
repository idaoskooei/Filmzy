package com.myapp.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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
import com.myapp.myapplication.composables.MyTopAppBar
import com.myapp.myapplication.home.category.CategoryScreen
import com.myapp.myapplication.home.HomeScreen
import com.myapp.myapplication.home.HomeViewModel
import com.myapp.myapplication.home.SearchScreen
import com.myapp.myapplication.profile.ProfileScreen
import com.myapp.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
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
                viewModel = SignUpViewModel(
                    authRepository = AuthRepository(),
                    navController = navController
                )
            )
        }

        composable("home_screen") {
            HomeScreen( viewModel = HomeViewModel(navController))
        }

        composable("my_top_app_bar") {
            MyTopAppBar(navController = navController)
        }

        composable("profile_screen") {
            ProfileScreen(navController = navController, displayName = "")
        }

        composable("search_screen") {
            SearchScreen()
        }
        composable("category_screen") {
            CategoryScreen()
        }
    }
}
