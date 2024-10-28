package com.example.cooking.data.models

import com.google.gson.annotations.SerializedName

data class RegInfo(
    @SerializedName("login")
    val login: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,
)

