package com.example.cooking

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cooking.network.CookingAPI
import com.example.cooking.ui.theme.CookingTheme
import com.example.cooking.view.AllRecipesModel
import com.example.cooking.view.AllRecipesView
import com.example.cooking.view.AuthView
import com.example.cooking.view.RegView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CookingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Greeting()
                }
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun Greeting() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = NavigationDestinations.Auth.route) {
        composable(NavigationDestinations.Auth.route) {
            AuthView(navController)
        }
        composable(NavigationDestinations.RegForm.route) {
            RegView(navController)
        }
        composable(NavigationDestinations.AllRecipe.route) {
            AllRecipesView(navController)
        }
    }
}