package com.example.cooking.view


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cooking.network.models.PostNetworkUserModel
import com.example.cooking.repositories.NetworkUserRepository
import com.example.cooking.repositories.UserRepository
import kotlinx.coroutines.launch

class RegModel(val userRepository: UserRepository) : ViewModel() {

    val addLogin = mutableStateOf("")
    val addEmail= mutableStateOf("")
    val addPassword = mutableStateOf("")
    val errorMessage = mutableStateOf("")
    val isLoading = mutableStateOf(false)

    fun addUser() {
        if (!validateInput()) {
            isLoading.value = true }
        viewModelScope.launch {
             try {
                 userRepository.addUser(
                     PostNetworkUserModel(
                         login = addLogin.value,
                         email = addEmail.value,
                         password = addPassword.value)
                 )
            } catch (e: Exception) {
                errorMessage.value = "Ошибка при добавлении пользователя: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
        }
    private fun validateInput(): Boolean {
        return when {
            addLogin.value.isEmpty() -> {
                errorMessage.value = "Логин не может быть пустым"
                false
            }

            addEmail.value.isEmpty() -> {
                errorMessage.value = "Email не может быть пустым"
                false
            }

            addPassword.value.isEmpty() -> {
                errorMessage.value = "Пароль не может быть пустым"
                false
            }

            else -> {
                errorMessage.value = ""
                true
            }
        }
    }
    private fun clearFields() {
        addLogin.value = ""
        addEmail.value = ""
        addPassword.value = ""
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegModel(NetworkUserRepository()) as T
        }
    }

}