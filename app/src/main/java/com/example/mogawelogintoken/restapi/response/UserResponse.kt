package com.example.mogawelogintoken.restapi.response

import com.example.mogawelogintoken.restapi.model.UserModel
import com.google.gson.annotations.SerializedName

data class UserResponse(
    //apa saja yg mw di eksekusi saat login berhasil masuk ke dalam
    @SerializedName("returnValue")
    val returnValue: String,
    @SerializedName("message")
    val messege: String,
    @SerializedName("object")
    val objectUser: UserModel,
)
