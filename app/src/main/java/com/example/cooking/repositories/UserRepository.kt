package com.example.cooking.repositories

import com.example.cooking.network.models.LoginUserModel
import com.example.cooking.network.models.PostNetworkUserModel

interface UserRepository {
    suspend fun addUser(user: PostNetworkUserModel)

    suspend fun userLogin(user: LoginUserModel)
}