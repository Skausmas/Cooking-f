package com.example.cooking.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cooking.network.models.LoginUserModel
import com.example.cooking.repositories.NetworkUserRepository
import com.example.cooking.repositories.UserRepository
import kotlinx.coroutines.launch

class AuthModel(val userRepository: UserRepository) : ViewModel() {


    val Login = mutableStateOf("")
    val Password = mutableStateOf("")


    fun LoginUser() {
        viewModelScope.launch {
                userRepository.userLogin(
                    LoginUserModel(
                        login = Login.value,
                        password = Password.value)
                )
        }
    }

    companion object Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RegModel(NetworkUserRepository()) as T
        }
    }

}