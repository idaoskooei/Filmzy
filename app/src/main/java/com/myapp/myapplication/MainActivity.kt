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
import com.myapp.myapplication.home.HomeScreen
import com.myapp.myapplication.home.HomeViewModel
import com.myapp.myapplication.home.category.CategoryScreen
import com.myapp.myapplication.home.category.CategoryViewModel
import com.myapp.myapplication.home.category.MovieListScreen
import com.myapp.myapplication.home.category.repo.CategoryRemoteService
import com.myapp.myapplication.home.category.repo.CategoryRepository
import com.myapp.myapplication.home.search.SearchScreen
import com.myapp.myapplication.home.search.SearchViewModel
import com.myapp.myapplication.home.search.repo.SearchRemoteService
import com.myapp.myapplication.home.search.repo.SearchRepository
import com.myapp.myapplication.movie.API_TOKEN
import com.myapp.myapplication.movie.BASE_URL
import com.myapp.myapplication.profile.ProfileScreen
import com.myapp.myapplication.profile.ProfileViewModel
import com.myapp.myapplication.ui.theme.MyApplicationTheme
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

val client = OkHttpClient.Builder().addNetworkInterceptor(object : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request: Request = chain.request()

        val newRequest: Request = request.newBuilder().addHeader("Authorization", API_TOKEN).build()
        return chain.proceed(newRequest)
    }
}).build()


val retrofit = Retrofit.Builder().client(client).baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create()).build()

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
        composable("profile_screen") {
            ProfileScreen(
                viewModel = ProfileViewModel(
                    navController,
                    authRepository = authRepository
                )
            )
        }
        composable("search_screen") {
            SearchScreen(
                viewModel = SearchViewModel(
                    SearchRepository(
                        retrofit.create(
                            SearchRemoteService::class.java
                        )
                    )
                )
            )
        }
        composable("category_screen") {
            CategoryScreen(
                navController = navController, viewModel = CategoryViewModel(
                    CategoryRepository(
                        retrofit.create(
                            CategoryRemoteService::class.java
                        )
                    )
                )
            )
        }
        composable("movie_list_screen") {
            MovieListScreen(movies = emptyList(), onMovieClick = {})
        }
    }
}
