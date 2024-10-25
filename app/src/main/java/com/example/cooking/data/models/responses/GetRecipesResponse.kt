package com.example.cooking.data.models.responses

import com.example.cooking.data.models.Recipe

data class GetRecipesResponse(
    val success: Boolean,
    val recipes: List<Recipe>
)
