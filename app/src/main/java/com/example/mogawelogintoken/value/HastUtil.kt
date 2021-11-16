package com.example.mogawelogintoken.value

import java.security.MessageDigest

//membuat sha256 agar password bisa masuk
fun String.sha256(): String {
    return hashString(this, "SHA256")
}

//hash memiliki 2 parameter
private fun hashString(input: String, algoritma: String): String {
    return MessageDigest
        .getInstance(algoritma)
        .digest(input.toByteArray())
        .fold("", { str, it -> str + "%02x".format(it) })
}