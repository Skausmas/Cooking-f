package com.example.cooking.ui.screens.edit_recipe.mvi

data class EditRecipesState(
    val isLoading: Boolean = false,
    val inputData: InputData = InputData()
)


data class InputData(
    val name: String = "",
    val ingredients: String = "",
    val text: String = "",
    val category: String = ""
)