package com.example.cooking.data.repositories

import com.example.cooking.data.models.Recipe
import com.example.cooking.data.network.CookingApiClient
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val api: CookingApiClient) {
    suspend fun getRecipes(): Result<List<Recipe>> {
        val response = api.getRecipes()
        if (!response.isSuccessful) {
            return Result.failure(RuntimeException("body is null"))
        }
        val recipeList = response.body() ?: return Result.failure(RuntimeException("body is null"))
        return Result.success(recipeList)
    }
}
