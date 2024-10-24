package com.example.cooking.network

import com.example.cooking.datamodel.Recipe
import com.example.cooking.network.models.LoginUserModel
import com.example.cooking.network.models.PostNetworkUserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CookingAPI {
    @POST("/users")
    suspend fun addUser(@Body user: PostNetworkUserModel)

    @GET("/login")
    suspend fun loginUser(@Body userModel: LoginUserModel)

    @GET("/recepi")
    suspend fun getRecipes(): Call<List<Recipe>>


}