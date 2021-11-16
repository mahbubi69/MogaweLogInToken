package com.example.mogawelogintoken.di

import com.example.mogawelogintoken.restapi.UserService
import com.example.mogawelogintoken.value.Value
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//hilt adalah pemanggilan belakang secara otomatis
//memakai system injection karena lebih enak ketika pemanggilan class"
//lain dan tidak memakan waktu banyak untuk pengkodean dan lebih cepat
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    //menyediakan httpClient
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    //build retrofit dan menyediakan retrofit
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Value.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    //pelayanan
    fun providePostUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}