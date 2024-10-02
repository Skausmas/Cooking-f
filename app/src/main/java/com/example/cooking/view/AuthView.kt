package com.example.cooking.view


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cooking.NavigationDestinations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthView(navController: NavController) {

    val model: AuthModel = viewModel(factory = AuthModel.Factory)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Вход") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Добро пожаловать!",
                fontSize = 24.sp )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = model.Login.value,
                onValueChange = { text ->model.Login.value = text },
                label = { Text("Логин") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = model.Password.value,
                onValueChange = { model.Password.value = it },
                label = { Text("Пароль") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate(
                    NavigationDestinations.AllRecipe.route
                )  },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Войти")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Нет аккаунта? Зарегистрироваться",
                modifier = Modifier
                    .clickable { navController.navigate(
                        NavigationDestinations.RegForm.route
                        ) }
                    .padding(8.dp)
            )
        }
    }
}