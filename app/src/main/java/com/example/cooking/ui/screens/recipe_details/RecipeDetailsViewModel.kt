package com.example.cooking.ui.screens.recipe_details

import androidx.lifecycle.ViewModel
import com.example.cooking.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(private val repository: UserRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(RecipeDetailsScreenState())
    val state: StateFlow<RecipeDetailsScreenState> get() = _state.asStateFlow()

    fun checkOwner(userId: Int) {
        val isOwner = repository.getCurrentUserId() == userId
        _state.update { it.copy(isOwner = isOwner) }
    }


}