package com.example.cooking.data.repositories

import com.example.cooking.common.hashMd5
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
            val response = api.registerUser(regInfo.copy(password = regInfo.password.hashMd5()))
            if (response.success) {
                preferenceManager.savedLogin = regInfo.login
                preferenceManager.userId = response.userId

            }
            response.success
        } catch (e: Exception) {
            false
        }
    }

    suspend fun loginUser(loginInfo: LoginInfo): Boolean {
        return try {
            val response = api.loginUser(loginInfo.copy(password = loginInfo.password.hashMd5()))
            if (response.success) {
                preferenceManager.savedLogin = loginInfo.login
                preferenceManager.userId = response.userId
            }
            response.success
        } catch (e: Exception) {
            false
        }
    }

    fun clearLoginData() {
        preferenceManager.savedLogin = ""
        preferenceManager.userId = -1
    }

    fun isLogIn(): Boolean {
        return preferenceManager.userId != -1
    }

    fun getCurrentUserId(): Int {
        return preferenceManager.userId
    }


}