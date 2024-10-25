package com.example.cooking

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cooking.ui.navigation.NavigationDestinations
import com.example.cooking.ui.screens.auth.LoginScreen
import com.example.cooking.ui.screens.auth.RegScreen
import com.example.cooking.ui.screens.receips.RecipesScreen
import com.example.cooking.ui.theme.CookingTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Navigator()
                }
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun Navigator() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = NavigationDestinations.Auth.route) {
        composable(NavigationDestinations.Auth.route) {
            LoginScreen(navController)
        }
        composable(NavigationDestinations.RegForm.route) {
            RegScreen(navController)
        }
        composable(NavigationDestinations.AllRecipe.route) {
            RecipesScreen(navController)
        }
    }
}