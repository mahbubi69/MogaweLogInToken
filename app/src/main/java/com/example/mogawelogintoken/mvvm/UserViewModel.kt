package com.example.mogawelogintoken.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mogawelogintoken.repository.UserRepository
import com.example.mogawelogintoken.restapi.response.ApiResponse
import com.example.mogawelogintoken.restapi.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


//view model secara injection otomatis (hilt dagger)
//view model agar secara arsitektur yang menjaga keamanan UI
//misal seperti tidak membolak balikkan hp tidak hilang data
@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    //mengambil data akun ketika sudah berhasil login
    //harus ada token dana menngunakan construktor token
    fun getDataUserDetail(token: String): LiveData<ApiResponse<UserResponse>> = runBlocking {
        //melakukan mmv saat get user sudah bekerja
        val userJob = async { userRepository.getUserData(token) }
        runBlocking {
            userJob.await().asLiveData()
        }
    }
}