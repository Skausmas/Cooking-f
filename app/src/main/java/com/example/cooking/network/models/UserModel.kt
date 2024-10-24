package com.example.cooking.network.models


data class GetNetworkUserModel(
    val id: Int,
    val login: String,
    val email: String,
    val password: String,
)

data class PostNetworkUserModel(
    val login: String,
    val email: String,
    val password: String
)

data class LoginUserModel(
    val login: String,
    val password: String
)

