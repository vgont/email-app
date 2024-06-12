package br.com.fiap.email_app.models

import java.util.Date


data class Email(
    val id: Int,
    val user: User,
    val title: String,
    val message: String,
    val date: Date
)
