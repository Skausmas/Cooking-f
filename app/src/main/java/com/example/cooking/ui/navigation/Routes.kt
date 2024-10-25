package com.example.cooking.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.cooking.data.models.Recipe
import com.example.cooking.ui.screens.auth.LoginScreen
import com.example.cooking.ui.screens.auth.RegScreen
import com.example.cooking.ui.screens.edit_recipe.EditRecipeScreen
import com.example.cooking.ui.screens.recipe_details.RecipeDetails
import com.example.cooking.ui.screens.recipes_list.RecipesScreen

object Routes {
    data object LoginScreenRoute : Screen {
        @Composable
        override fun Content() {
            LoginScreen()
        }
    }

    data object RegScreenRoute : Screen {
        @Composable
        override fun Content() {
            RegScreen()
        }
    }

    data object RecipesScreenRoute : Screen {
        @Composable
        override fun Content() {
            RecipesScreen()
        }

    }

    data class EditRecipeScreenRoute(val recipe: Recipe?) : Screen {
        @Composable
        override fun Content() {
            EditRecipeScreen(recipe)
        }

    }

    data class RecipeDetailsRoute(val recipe: Recipe) : Screen {
        @Composable
        override fun Content() {
            RecipeDetails(recipe)
        }
    }
}