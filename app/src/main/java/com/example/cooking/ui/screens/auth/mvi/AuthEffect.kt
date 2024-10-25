package com.example.cooking.ui.screens.auth.mvi

sealed interface AuthEffect {
    data class ShowToast(val text: String) : AuthEffect
    data object NavigateToRecipes : AuthEffect
}
