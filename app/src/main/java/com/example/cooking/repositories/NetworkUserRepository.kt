package com.example.cooking.repositories
import com.example.cooking.network.models.LoginUserModel
import com.example.cooking.network.models.PostNetworkUserModel


class NetworkUserRepository : UserRepository {

    private val apiService = RetrofitClient.apiService


    override suspend fun addUser(user: PostNetworkUserModel){
    return apiService.addUser(user)
    }

    override suspend fun userLogin(user : LoginUserModel) {
        return apiService.loginUser(user)
    }

}