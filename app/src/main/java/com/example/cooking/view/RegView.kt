package com.example.cooking.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegView(navController: NavController) {

    val regModel: RegModel = viewModel(factory = RegModel.Factory)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Регистрация") }
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
                text = "Создайте аккаунт",
                fontSize = 24.sp)

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = regModel.addLogin.value,
                onValueChange = { text -> regModel.addLogin.value = text },
                label = { Text("Логин") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = regModel.addEmail.value,
                onValueChange = { text -> regModel.addEmail.value = text },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = regModel.addPassword.value,
                onValueChange = { text -> regModel.addPassword.value = text  },
                label = { Text("Пароль") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done )
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (regModel.errorMessage.value.isNotEmpty()) {
                Text(
                    text = regModel.errorMessage.value,
                    color = Color.Red,
                    modifier = Modifier.padding(10.dp)
                )

                CircularProgressIndicator(modifier = Modifier.padding(10.dp))
            } else {
                Button(
                    onClick = {regModel.addUser() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Зарегистрироваться")
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Уже есть аккаунт? Войти",
                modifier = Modifier
                    .clickable { /* TODO: Navigate to login */ }
                    .padding(8.dp)
            )
        }
    }
}
