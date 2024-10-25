package com.example.cooking.data.models

data class Recipe(
    val id: Int,
    val name: String,
    val category: String,
    val ingredients: String,
    val text: String,
    val userId: Int,
    val userName: String
)
