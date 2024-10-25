package com.example.cooking.data.repositories

import com.example.cooking.data.local.PreferenceManager
import com.example.cooking.data.models.CreateRecipeInfo
import com.example.cooking.data.models.Recipe
import com.example.cooking.data.models.UpdateRecipeInfo
import com.example.cooking.data.models.toCreateRecipeInfoData
import com.example.cooking.data.network.CookingApiClient
import kotlinx.coroutines.delay
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api: CookingApiClient,
    private val preferenceManager: PreferenceManager
) {
    suspend fun getRecipes(): Result<List<Recipe>> {
//        val response =
//            api.getRecipes()
//        if (!response.isSuccessful) {
//            return Result.failure(RuntimeException("body is null"))
//        }
        val recipeList =
            listOf(
                Recipe(
                    id = 1,
                    name = "Паста с томатным соусом",
                    category = "Основное блюдо",
                    ingredients = "Паста",
                    text = "Это простой и вкусный рецепт пасты с томатным соусом.",
                    userId = 1,
                    userName = "developer"
                ),

                Recipe(
                    id = 2,
                    name = "карбонара",
                    category = "Основное блюдо",
                    ingredients = "Паста",
                    text = "Это простой и вкусный рецепт карбонары.",
                    userId = 1,
                    userName = "developer"

                )
            )
        delay(1000)
//            response.body() ?: return Result.failure(RuntimeException("body is null"))
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

