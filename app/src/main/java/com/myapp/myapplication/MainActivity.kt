package com.myapp.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.myapp.myapplication.ui.navigation.NavGraph
import com.myapp.myapplication.ui.navigation.NavigationManager
import com.myapp.myapplication.ui.navigation.NavigationManagerImpl
import com.myapp.myapplication.ui.theme.FilmzyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FilmzyTheme {
                val navController = rememberNavController()
                (navigationManager as NavigationManagerImpl).setup(navController)
                NavGraph(navController = navController)
            }
        }
    }
}