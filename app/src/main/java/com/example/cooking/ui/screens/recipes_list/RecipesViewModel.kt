package com.example.cooking.ui.screens.recipes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooking.data.repositories.RecipeRepository
import com.example.cooking.data.repositories.UserRepository
import com.example.cooking.ui.screens.recipes_list.mvi.RecipesEffect
import com.example.cooking.ui.screens.recipes_list.mvi.RecipesEvent
import com.example.cooking.ui.screens.recipes_list.mvi.RecipesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    userRepository: UserRepository
) : ViewModel() {


    private val _recipesState = MutableStateFlow(
        RecipesState(
            isLogIn = userRepository.isLogIn()
        )
    )
    val recipesState: StateFlow<RecipesState> get() = _recipesState.asStateFlow()

    private val _recipesEffect = Channel<RecipesEffect>()
    val recipesEffect: Flow<RecipesEffect> get() = _recipesEffect.consumeAsFlow()

    fun obtainEvent(event: RecipesEvent) {
        when (event) {
            RecipesEvent.LoadRecipes -> {
                _recipesState.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    try {
                        val recipesResult = recipeRepository.getRecipes()
                        recipesResult.onSuccess { recipes ->
                            _recipesState.update { it.copy(currentRecipesList = recipes) }
                        }
                        recipesResult.onFailure { e ->
                            _recipesEffect.send(RecipesEffect.ShowToast("fail while loading recipes: $e"))
                        }
                    } catch (e: Exception) {
                        _recipesEffect.send(RecipesEffect.ShowToast("fail while loading recipes: $e"))
                    } finally {
                        _recipesState.update { it.copy(isLoading = false) }
                    }
                }
            }
        }

    }
}