package com.example.cooking.ui.screens.recipes_list.mvi

import com.example.cooking.data.models.Recipe

data class RecipesState(
    val currentRecipesList: List<Recipe> = listOf(),
    val isLoading: Boolean = false,
    val isLogIn: Boolean = false
)
