package com.example.mogawelogintoken.restapi.model

import com.google.gson.annotations.SerializedName

data class LogInSubmit(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
)
