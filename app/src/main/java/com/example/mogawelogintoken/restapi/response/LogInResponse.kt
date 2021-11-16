package com.example.mogawelogintoken.restapi.response

import com.google.gson.annotations.SerializedName

data class LogInResponse(
    @SerializedName("returnValue")
    val returnValue: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String
)
