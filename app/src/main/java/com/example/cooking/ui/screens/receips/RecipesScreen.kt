package com.example.cooking.ui.screens.receips

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cooking.data.models.Recipe


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesScreen(navController: NavHostController) {
    val recipes = listOf(
        Recipe(
            name = "Паста с томатным соусом",
            category = "Основное блюдо",
            ingredients = "Паста",
            text = "Это простой и вкусный рецепт пасты с томатным соусом.",
            userId = 1
        )
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Рецепты") },
            )
        }) { paddings ->
        Box(modifier = Modifier.padding(paddings)) {
            RecipeList(recipes)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun AllRecipesView(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* TODO: Handle click on recipe */ },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = recipe.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = recipe.category, fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Ингредиенты:", fontWeight = FontWeight.Bold)
            recipe.ingredients
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = recipe.text, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Автор: ${recipe.userId}",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            ),
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeList(recipes: List<Recipe>) {
    LazyColumn {
        items(recipes) { recipe ->
            AllRecipesView(recipe = recipe)
        }
    }
}


