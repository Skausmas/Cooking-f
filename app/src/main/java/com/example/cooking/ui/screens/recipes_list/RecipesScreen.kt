package com.example.cooking.ui.screens.recipes_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.cooking.R
import com.example.cooking.data.models.Recipe
import com.example.cooking.ui.navigation.Routes
import com.example.cooking.ui.screens.recipes_list.mvi.RecipesEffect
import com.example.cooking.ui.screens.recipes_list.mvi.RecipesEvent
import com.example.cooking.ui.theme.Styles


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesScreen(
    navigator: Navigator = LocalNavigator.currentOrThrow,
    viewModel: RecipesViewModel = hiltViewModel()
) {

    val state = viewModel.recipesState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect("side effects") {
        viewModel.recipesEffect.collect { effect ->
            when (effect) {

                is RecipesEffect.ShowToast -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    LaunchedEffect("updateLoginData") {
        viewModel.obtainEvent(RecipesEvent.UpdateLoginData)
    }

    LaunchedEffect("load recipes") {
        viewModel.obtainEvent(RecipesEvent.LoadRecipes)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigator.push(
                    if (state.value.isLogIn) {
                        Routes.EditRecipeScreenRoute(null)
                    } else
                        Routes.LoginScreenRoute
                )
            }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "add"
                )
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Рецепты", style = Styles.titleTextStyle) },
            )
        }) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
            contentAlignment = Alignment.Center
        ) {


            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.value.currentRecipesList) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        onCardClick = {
                            navigator.push(Routes.RecipeDetailsRoute(recipe))
                        }
                    )
                }
            }

            if (state.value.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun RecipeCard(recipe: Recipe, onCardClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onCardClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(text = recipe.name, style = Styles.titleTextStyle)
            Text(
                text = recipe.category,
                style = Styles.commonTextStyle.copy(fontStyle = FontStyle.Italic)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ингредиенты: ${recipe.ingredients}", style = Styles.commonTextStyle)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = recipe.text, style = Styles.commonTextStyle)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Автор: ${recipe.userName}",
                style = Styles.commonTextStyle,
            )
        }
    }
}




