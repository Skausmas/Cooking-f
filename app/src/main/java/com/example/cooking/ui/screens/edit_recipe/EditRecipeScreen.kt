package com.example.cooking.ui.screens.edit_recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.cooking.R
import com.example.cooking.data.models.Recipe
import com.example.cooking.ui.navigation.Routes
import com.example.cooking.ui.screens.edit_recipe.mvi.EditRecipeEffect
import com.example.cooking.ui.screens.edit_recipe.mvi.EditRecipeEvent
import com.example.cooking.ui.screens.edit_recipe.mvi.InputData
import com.example.cooking.ui.theme.Styles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditRecipeScreen(
    recipe: Recipe?,
    viewModel: EditRecipeViewModel = hiltViewModel(),
    navigator: Navigator = LocalNavigator.currentOrThrow
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state = viewModel.editRecipesState.collectAsState()

    LaunchedEffect("update inputs") {
        recipe?.let { recipe ->
            viewModel.obtainEvent(
                EditRecipeEvent.UpdateInputData(
                    InputData(
                        name = recipe.name,
                        category = recipe.category,
                        text = recipe.text,
                        ingredients = recipe.ingredients
                    )
                )
            )
        }
    }

    LaunchedEffect("side effects") {
        viewModel.editRecipesEffect.collect { effect ->
            when (effect) {
                is EditRecipeEffect.NavigateToRecipe -> {
                    if (recipe != null)
                        navigator.pop()
                    navigator.replace(Routes.RecipeDetailsRoute(effect.recipe))
                }

                is EditRecipeEffect.ShowToast -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (recipe != null) "изменить ${recipe.name}" else "Создать",
                        style = Styles.titleTextStyle
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                navigator.pop()
                            }
                            .padding(8.dp)
                            .size(24.dp),
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = "back"
                    )
                }
            )
        },


        ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.height(64.dp))


            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Название",
                style = Styles.titleTextStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.value.inputData.name,
                onValueChange = { text ->
                    viewModel.obtainEvent(
                        EditRecipeEvent.UpdateInputData(
                            state.value.inputData.copy(name = text)
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(32.dp))



            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Категория",
                style = Styles.titleTextStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.value.inputData.category,
                onValueChange = { text ->
                    viewModel.obtainEvent(
                        EditRecipeEvent.UpdateInputData(
                            state.value.inputData.copy(category = text)
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(32.dp))



            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Ингредиенты",
                style = Styles.titleTextStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.value.inputData.ingredients,
                onValueChange = { text ->
                    viewModel.obtainEvent(
                        EditRecipeEvent.UpdateInputData(
                            state.value.inputData.copy(ingredients = text)
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.height(32.dp))


            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Способ приготовления",
                style = Styles.titleTextStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = state.value.inputData.text,
                onValueChange = { text ->
                    viewModel.obtainEvent(
                        EditRecipeEvent.UpdateInputData(
                            state.value.inputData.copy(text = text)
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.obtainEvent(EditRecipeEvent.EditRecipe(recipe?.id ?: -1))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Сохранить")
                    if (state.value.isLoading) {
                        Spacer(modifier = Modifier.width(16.dp))
                        CircularProgressIndicator(
                            modifier = Modifier.size(16.dp),
                            strokeWidth = 2.dp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }

}