package com.myapp.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.auth.model.AuthRepository
import com.myapp.myapplication.navigation.NavGraph
import com.myapp.myapplication.ui.theme.FilmzyTheme
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FilmzyTheme {
                    val navController = rememberNavController()
                    NavGraph(navController = navController, authRepository = AuthRepository())
            }
        }
    }
}

val client = OkHttpClient.Builder().addNetworkInterceptor(Interceptor { chain ->
    val request: Request = chain.request()

    val newRequest: Request = request.newBuilder().addHeader("Authorization", API_TOKEN).build()
    chain.proceed(newRequest)
})
    .addInterceptor(HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    })
    .build()


val retrofit = Retrofit.Builder().client(client).baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create()).build()