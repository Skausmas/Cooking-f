package com.example.cooking.ui.screens.edit_recipe.mvi

import com.example.cooking.data.models.Recipe

sealed interface EditRecipeEffect {
    data class ShowToast(val message: String) : EditRecipeEffect
    data class NavigateToRecipe(val recipe: Recipe) : EditRecipeEffect
}