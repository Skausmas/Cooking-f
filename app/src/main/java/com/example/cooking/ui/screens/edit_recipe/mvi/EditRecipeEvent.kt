package com.example.cooking.ui.screens.edit_recipe.mvi

sealed interface EditRecipeEvent {
    data class EditRecipe(val id: Int) : EditRecipeEvent
    data class UpdateInputData(val inputData: InputData) : EditRecipeEvent
}