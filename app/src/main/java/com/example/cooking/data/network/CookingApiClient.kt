package com.example.cooking.data.network

import com.example.cooking.data.models.CreateRecipeInfoData
import com.example.cooking.data.models.LoginInfo
import com.example.cooking.data.models.Recipe
import com.example.cooking.data.models.RegInfo
import com.example.cooking.data.models.UpdateRecipeInfo
import com.example.cooking.data.models.responses.AuthResponse
import com.example.cooking.data.models.responses.GetRecipesResponse
import com.example.cooking.data.models.responses.RecipeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CookingApiClient {
    @POST("users")
    suspend fun registerUser(@Body user: RegInfo): AuthResponse

    @GET("login")
    suspend fun loginUser(@Body userModel: LoginInfo): AuthResponse

    @GET("recepi")
    suspend fun getRecipes(): GetRecipesResponse

    @POST("editRecipe")
    suspend fun editRecipe(@Body updateInfo: UpdateRecipeInfo): RecipeResponse

    @POST("createRecipe")
    suspend fun createRecipe(@Body createRecipeInfo: CreateRecipeInfoData): RecipeResponse

}

class StubApi : CookingApiClient {
    private var id = 0
    override suspend fun registerUser(user: RegInfo): AuthResponse {
        return AuthResponse(
            success = true,
            userId = 777
        )
    }

    override suspend fun loginUser(userModel: LoginInfo): AuthResponse {
        return AuthResponse(
            success = true,
            userId = 777
        )
    }

    override suspend fun getRecipes(): GetRecipesResponse {
        return GetRecipesResponse(
            success = true,
            recipes = listOf(
                Recipe(
                    id = 1,
                    name = "Паста с томатным соусом",
                    category = "Основное блюдо",
                    ingredients = "Паста",
                    text = "Это простой и вкусный рецепт пасты с томатным соусом.",
                    userId = 777,
                    userName = "developer"
                ),

                Recipe(
                    id = 2,
                    name = "карбонара",
                    category = "Основное блюдо",
                    ingredients = "Паста",
                    text = "Это простой и вкусный рецепт карбонары.",
                    userId = 7,
                    userName = "other"

                )
            )
        )
    }

    override suspend fun editRecipe(updateInfo: UpdateRecipeInfo): RecipeResponse {
        return RecipeResponse(
            success = true,
            newRecipe = Recipe(
                id = updateInfo.id,
                name = updateInfo.name,
                category = updateInfo.category,
                ingredients = updateInfo.ingredients,
                text = updateInfo.text,
                userId = 7,
                userName = "other"
            )
        )
    }

    override suspend fun createRecipe(createRecipeInfo: CreateRecipeInfoData): RecipeResponse {
        return RecipeResponse(
            success = true,
            newRecipe = Recipe(
                id = ++id,
                name = createRecipeInfo.name,
                category = createRecipeInfo.category,
                ingredients = createRecipeInfo.ingredients,
                text = createRecipeInfo.text,
                userId = 777,
                userName = "other"
            )
        )
    }

}