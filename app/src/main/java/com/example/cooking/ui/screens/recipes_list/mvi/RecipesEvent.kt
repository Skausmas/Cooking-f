package com.example.cooking.ui.screens.recipes_list.mvi

sealed interface RecipesEvent {
    data object LoadRecipes : RecipesEvent
    data object UpdateLoginData : RecipesEvent
}