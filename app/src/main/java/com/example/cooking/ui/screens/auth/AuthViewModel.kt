package com.example.cooking.ui.screens.auth


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooking.data.models.LoginInfo
import com.example.cooking.data.models.RegInfo
import com.example.cooking.data.repositories.UserRepository
import com.example.cooking.ui.screens.auth.mvi.AuthEffect
import com.example.cooking.ui.screens.auth.mvi.AuthEvent
import com.example.cooking.ui.screens.auth.mvi.AuthState
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
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> get() = _authState.asStateFlow()

    private val _authEffect = Channel<AuthEffect>()
    val authEffect: Flow<AuthEffect> get() = _authEffect.consumeAsFlow()

    fun obtainEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.RegisterUser -> {
                _authState.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    if (!validateRegInput(event.regInfo)) {
                        _authState.update { it.copy(isLoading = false) }
                    } else try {
                        val success = userRepository.registerUser(
                            event.regInfo
                        )
                        if (success) {
                            _authState.update { it.copy(isLoading = false) }
                            _authEffect.send(AuthEffect.NavigateToRecipes)

                        } else {
                            _authState.update { it.copy(isLoading = false) }
                            _authEffect.send(AuthEffect.ShowToast("Ошибка при добавлении пользователя"))
                        }


                    } catch (e: Exception) {
                        _authState.update { it.copy(isLoading = false) }
                        _authEffect.send(AuthEffect.ShowToast("Ошибка при добавлении пользователя: ${e.message}"))
                    }
                }
            }

            is AuthEvent.LoginUser -> {
                _authState.update { it.copy(isLoading = true) }
                viewModelScope.launch {
                    if (!validateLoginInput(event.loginInfo)) {
                        _authState.update { it.copy(isLoading = false) }
                    } else try {
                        val success = userRepository.loginUser(
                            event.loginInfo
                        )
                        if (success) {
                            _authState.update { it.copy(isLoading = false) }
                            _authEffect.send(AuthEffect.NavigateToRecipes)

                        } else {
                            _authState.update { it.copy(isLoading = false) }
                            _authEffect.send(AuthEffect.ShowToast("Ошибка авторизации"))
                        }


                    } catch (e: Exception) {
                        _authState.update { it.copy(isLoading = false) }
                        _authEffect.send(AuthEffect.ShowToast("Ошибка авторизации: ${e.message}"))
                    }
                }
            }
        }
    }

    private suspend fun validateRegInput(regInfo: RegInfo): Boolean {
        return when {
            regInfo.login.isEmpty() -> {
                _authEffect.send(AuthEffect.ShowToast("Логин не может быть пустым"))
                false
            }

            regInfo.email.isEmpty() -> {
                _authEffect.send(AuthEffect.ShowToast("Email не может быть пустым"))
                false
            }

            regInfo.password.isEmpty() -> {
                _authEffect.send(AuthEffect.ShowToast("Пароль не может быть пустым"))
                false
            }

            else -> {
                true
            }
        }
    }

    private suspend fun validateLoginInput(loginInfo: LoginInfo): Boolean {
        return when {
            loginInfo.login.isEmpty() -> {
                _authEffect.send(AuthEffect.ShowToast("Логин не может быть пустым"))
                false
            }


            loginInfo.password.isEmpty() -> {
                _authEffect.send(AuthEffect.ShowToast("Пароль не может быть пустым"))
                false
            }

            else -> {
                true
            }
        }
    }

}