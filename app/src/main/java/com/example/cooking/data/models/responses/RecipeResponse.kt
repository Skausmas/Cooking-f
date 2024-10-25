package com.example.cooking.data.models.responses

import com.example.cooking.data.models.Recipe

data class RecipeResponse(
    val success: Boolean,
    val newRecipe: Recipe
)
