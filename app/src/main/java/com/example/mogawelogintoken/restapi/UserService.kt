package com.example.mogawelogintoken.restapi

import com.example.mogawelogintoken.restapi.model.LogInSubmit
import com.example.mogawelogintoken.restapi.response.LogInResponse
import com.example.mogawelogintoken.restapi.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    //login
    @POST("api/mogawers/logIn")
    suspend fun logInUser(
        @Body submit: LogInSubmit,
    ): LogInResponse


    //get
    @POST("api/mogawers/v2/getProfile")
    suspend fun getUserAccount(
        @Header("token") token: String,
    ): UserResponse
}