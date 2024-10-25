package com.example.cooking.data.network

import com.example.cooking.data.models.LoginInfo
import com.example.cooking.data.models.Recipe
import com.example.cooking.data.models.RegInfo
import com.example.cooking.data.models.responses.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CookingApiClient {
    @POST("users")
    suspend fun registerUser(@Body user: RegInfo): AuthResponse

    @GET("login")
    suspend fun loginUser(@Body userModel: LoginInfo): AuthResponse

    @GET("recepi")
    suspend fun getRecipes(): Response<List<Recipe>>

}