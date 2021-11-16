package com.example.mogawelogintoken.restapi.response

//sealed class untuk pemanggilan pada package saja
//T bebas menyimpan apa aja
//class ini di gunakan untuk merespon ketika mau eksekusi ke login,logout,dll
sealed class ApiResponse<out R> {
    class Loading<T>() : ApiResponse<T>()
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessege: String) : ApiResponse<Nothing>()
}