package com.example.mogawelogintoken

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

//Semua aplikasi yang menggunakan Hilt harus berisi class Application yang dianotasi dengan @HiltAndroidApp.
//@HiltAndroidApp memicu pembuatan kode Hilt,
//termasuk class dasar untuk aplikasi Anda yang berfungsi sebagai container dependensi tingkat aplikasi.
@HiltAndroidApp
open class BaseApk : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}