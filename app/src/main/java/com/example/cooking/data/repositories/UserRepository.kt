package com.example.cooking.data.repositories

import com.example.cooking.data.local.PreferenceManager
import com.example.cooking.data.models.LoginInfo
import com.example.cooking.data.models.RegInfo
import com.example.cooking.data.network.CookingApiClient
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val api: CookingApiClient,
    private val preferenceManager: PreferenceManager
) {

    suspend fun registerUser(regInfo: RegInfo): Boolean {
        return try {
            val response = api.registerUser(regInfo)
            if (response.success) {
                preferenceManager.savedLogin = regInfo.login
            }
            response.success
        } catch (e: Exception) {
            false
        }
    }

    suspend fun loginUser(loginInfo: LoginInfo): Boolean {
        return try {
            val response = api.loginUser(loginInfo)
            if (response.success) {
                preferenceManager.savedLogin = loginInfo.login
            }
            response.success
        } catch (e: Exception) {
            false
        }
    }

    fun clearLogin() {
        preferenceManager.savedLogin = ""
    }
}