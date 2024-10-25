package com.example.cooking.ui.screens.recipe_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.cooking.R
import com.example.cooking.data.models.Recipe
import com.example.cooking.ui.navigation.Routes
import com.example.cooking.ui.theme.Styles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetails(
    recipe: Recipe,
    navigator: Navigator = LocalNavigator.currentOrThrow,
    viewModel: RecipeDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect("check recipe owner") {
        viewModel.checkOwner(recipe.userId)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = recipe.name, style = Styles.titleTextStyle)
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
        floatingActionButton = {
            if (state.value.isOwner)
                FloatingActionButton(onClick = {

                    navigator.push(Routes.EditRecipeScreenRoute(recipe))
                }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = "edit"
                    )
                }
        }

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

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = recipe.name,
                style = Styles.commonTextStyle
            )
            Spacer(modifier = Modifier.height(32.dp))



            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Категоря",
                style = Styles.titleTextStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = recipe.category,
                style = Styles.commonTextStyle
            )
            Spacer(modifier = Modifier.height(32.dp))



            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Ингредиенты",
                style = Styles.titleTextStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = recipe.ingredients,
                style = Styles.commonTextStyle
            )
            Spacer(modifier = Modifier.height(32.dp))


            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Способ приготовления",
                style = Styles.titleTextStyle
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = recipe.text,
                style = Styles.commonTextStyle
            )
            Spacer(modifier = Modifier.height(32.dp))

        }
    }
}