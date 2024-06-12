package br.com.fiap.email_app.models

import android.graphics.drawable.Icon

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val icon: Icon
)
