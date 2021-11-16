package com.example.mogawelogintoken.repository

import com.example.mogawelogintoken.restapi.UserService
import com.example.mogawelogintoken.restapi.model.LogInSubmit
import com.example.mogawelogintoken.restapi.response.ApiResponse
import com.example.mogawelogintoken.restapi.response.LogInResponse
import com.example.mogawelogintoken.restapi.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton


//pengolahan data yang secara terpisah
@Singleton
class UserRepository @Inject constructor(private val userService: UserService) {
    suspend fun logIn(submit: LogInSubmit): Flow<ApiResponse<LogInResponse>> {
        return flow {
            //emit mengirim data
            emit(ApiResponse.Loading())
            try {
                emit(ApiResponse.Loading())
                val submitLogin = userService.logInUser(submit)
                if (submitLogin.returnValue == "000") {
                    emit(ApiResponse.Success(submitLogin))
                } else {
                    emit(ApiResponse.Error(submitLogin.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                //timber hayan oean untuk programing saja
                Timber.e("$e")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUserData(token: String): Flow<ApiResponse<UserResponse>> {
        return flow {
            emit(ApiResponse.Loading())
            try {
                emit(ApiResponse.Loading())
                val responseUser = userService.getUserAccount(token)
                if (responseUser.returnValue == "000") {
                    emit(ApiResponse.Success(responseUser))
                } else {
                    emit(ApiResponse.Error(responseUser.messege))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Timber.e("$e")
            }
        }.flowOn(Dispatchers.IO)
    }
}