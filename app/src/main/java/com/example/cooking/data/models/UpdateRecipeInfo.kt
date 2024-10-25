package com.example.cooking.data.models

data class UpdateRecipeInfo(
    val id: Int,
    val name: String,
    val category: String,
    val ingredients: String,
    val text: String,
)

data class CreateRecipeInfo(
    val name: String,
    val category: String,
    val ingredients: String,
    val text: String,
)

data class CreateRecipeInfoData(
    val userId: Int,
    val name: String,
    val category: String,
    val ingredients: String,
    val text: String,
)

fun CreateRecipeInfo.toCreateRecipeInfoData(userId: Int): CreateRecipeInfoData {
    return CreateRecipeInfoData(
        userId, name, category, ingredients, text
    )
}
