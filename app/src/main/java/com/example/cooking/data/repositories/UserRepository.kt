package com.example.cooking.data.repositories

import com.example.cooking.data.models.LoginInfo
import com.example.cooking.data.models.RegInfo
import com.example.cooking.data.network.CookingApiClient
import javax.inject.Inject


class UserRepository @Inject constructor(private val api: CookingApiClient) {

    suspend fun registerUser(regInfo: RegInfo): Boolean {
        return try {
            val response = api.registerUser(regInfo)
            response.success
        } catch (e: Exception) {
            false
        }
    }

    suspend fun loginUser(loginInfo: LoginInfo): Boolean {
        return try {
            val response = api.loginUser(loginInfo)
            response.success
        } catch (e: Exception) {
            false
        }
    }
}