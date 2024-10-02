package com.example.cooking.repositories

import com.example.cooking.datamodel.Recipe
import com.example.cooking.network.CookingAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository (private val apiService: CookingAPI) {


    suspend fun getRecipes(callback: (List<Recipe>?) -> Unit) {
        apiService.getRecipes().enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null) // Обработка ошибки
                }
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                callback(null) // Обработка ошибки
            }
        }
    }
}
