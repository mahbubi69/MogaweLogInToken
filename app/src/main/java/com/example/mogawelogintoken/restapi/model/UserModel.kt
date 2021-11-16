package com.example.mogawelogintoken.restapi.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("termsAgreed")
    val termsAgreed: Boolean,

    @SerializedName("id_mogawers")
    val id_mogawers: Int,

    @SerializedName("uuid")
    val uuid: String,

    @SerializedName("mogawers_code")
    val mogawersCode: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("id_device")
    val idDevice: String,

    @SerializedName("profile_pictures")
    val profilPictures: Any? = null,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("birthdate")
    val birthDate: Int,

    @SerializedName("level")
    val level: Int,

    @SerializedName("gawean_selesai")
    val gaweanSelesai: Int,

    @SerializedName("douwnline_count")
    val douwntline_count: Int,

    @SerializedName("pending_payment")
    val pendingMayment: String,

    @SerializedName("edu")
    val edu: String,

    @SerializedName(" is_phone_active")
    val isPhoneActive: Boolean,

    @SerializedName("balance")
    val balance: Int,

    @SerializedName("point")
    val point: Int,
)
