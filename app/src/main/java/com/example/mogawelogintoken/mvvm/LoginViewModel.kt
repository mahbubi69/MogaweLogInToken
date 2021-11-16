package com.example.mogawelogintoken.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mogawelogintoken.repository.UserRepository
import com.example.mogawelogintoken.restapi.model.LogInSubmit
import com.example.mogawelogintoken.restapi.response.ApiResponse
import com.example.mogawelogintoken.restapi.response.LogInResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    fun logIn(submit: LogInSubmit): LiveData<ApiResponse<LogInResponse>> = runBlocking {
        //melakukan mvvm saat perkerjaan login masuk
        val logInjob = async { userRepository.logIn(submit) }
        runBlocking {
            logInjob.await().asLiveData()
        }
    }
}