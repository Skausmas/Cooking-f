package com.example.cooking.ui.screens.recipes_list.mvi

sealed interface RecipesEffect {
    data class ShowToast(val message: String) : RecipesEffect
}