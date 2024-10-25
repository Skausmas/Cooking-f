package com.example.cooking.ui.screens.edit_recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooking.data.models.CreateRecipeInfo
import com.example.cooking.data.models.UpdateRecipeInfo
import com.example.cooking.data.repositories.RecipeRepository
import com.example.cooking.ui.screens.edit_recipe.mvi.EditRecipeEffect
import com.example.cooking.ui.screens.edit_recipe.mvi.EditRecipeEvent
import com.example.cooking.ui.screens.edit_recipe.mvi.EditRecipesState
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
class EditRecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _editRecipesState = MutableStateFlow(EditRecipesState())
    val editRecipesState: StateFlow<EditRecipesState> get() = _editRecipesState.asStateFlow()

    private val _editRecipesEffect = Channel<EditRecipeEffect>()
    val editRecipesEffect: Flow<EditRecipeEffect> get() = _editRecipesEffect.consumeAsFlow()

    fun obtainEvent(event: EditRecipeEvent) {
        when (event) {
            is EditRecipeEvent.EditRecipe -> {
                _editRecipesState.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    try {
                        val inputData = _editRecipesState.value.inputData

                        val recipesResult = if (event.id == -1) { //creating new
                            repository.createRecipe(
                                createRecipeInfo = CreateRecipeInfo(
                                    name = inputData.name,
                                    category = inputData.category,
                                    ingredients = inputData.ingredients,
                                    text = inputData.text
                                )
                            )
                        } else
                            repository.updateRecipe(
                                updateRecipeInfo = UpdateRecipeInfo(
                                    id = event.id,
                                    name = inputData.name,
                                    category = inputData.category,
                                    ingredients = inputData.ingredients,
                                    text = inputData.text
                                )
                            )
                        recipesResult.onSuccess { recipe ->
                            _editRecipesEffect.send(EditRecipeEffect.NavigateToRecipe(recipe))
                        }
                        recipesResult.onFailure { e ->
                            _editRecipesEffect.send(EditRecipeEffect.ShowToast("fail while loading recipes: $e"))
                        }
                    } catch (e: Exception) {
                        _editRecipesEffect.send(EditRecipeEffect.ShowToast("fail while loading recipes: $e"))
                    } finally {
                        _editRecipesState.update { it.copy(isLoading = false) }
                    }
                }
            }

            is EditRecipeEvent.UpdateInputData -> {
                _editRecipesState.update { it.copy(inputData = event.inputData) }
            }
        }

    }
}