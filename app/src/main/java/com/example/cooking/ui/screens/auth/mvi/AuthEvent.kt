package com.example.cooking.ui.screens.auth.mvi

import com.example.cooking.data.models.LoginInfo
import com.example.cooking.data.models.RegInfo

sealed interface AuthEvent {
    data class RegisterUser(val regInfo: RegInfo) : AuthEvent
    data class LoginUser(val loginInfo: LoginInfo) : AuthEvent
}