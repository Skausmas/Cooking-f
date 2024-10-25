package com.example.cooking.data.repositories

import com.example.cooking.data.local.PreferenceManager
import com.example.cooking.data.models.CreateRecipeInfo
import com.example.cooking.data.models.Recipe
import com.example.cooking.data.models.UpdateRecipeInfo
import com.example.cooking.data.models.toCreateRecipeInfoData
import com.example.cooking.data.network.CookingApiClient
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api: CookingApiClient,
    private val preferenceManager: PreferenceManager
) {
    suspend fun getRecipes(): Result<List<Recipe>> {
        val response =
            api.getRecipes()
        if (!response.success) {
            return Result.failure(RuntimeException("response not success"))
        }
        val recipeList = response.recipes
        return Result.success(recipeList)
    }

    suspend fun updateRecipe(updateRecipeInfo: UpdateRecipeInfo): Result<Recipe> {
        val response = api.editRecipe(updateRecipeInfo)
        if (!response.success) {
            return Result.failure(RuntimeException("body is null"))
        }
        val newRecipe = response.newRecipe
        return Result.success(newRecipe)
    }

    suspend fun createRecipe(createRecipeInfo: CreateRecipeInfo): Result<Recipe> {
        val userId = preferenceManager.userId
        val response = api.createRecipe(createRecipeInfo.toCreateRecipeInfoData(userId))

        if (!response.success) {
            return Result.failure(RuntimeException("body is null"))
        }
        val newRecipe = response.newRecipe
        return Result.success(newRecipe)
    }
}

