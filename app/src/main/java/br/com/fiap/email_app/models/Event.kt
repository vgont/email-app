package br.com.fiap.email_app.models

import java.util.Date

data class Event(
    val id: Int,
    val title: String,
    val startsAt: Date,
    val endsAt: Date,
    val description: String,
    val place: String?,
    val participants: String?
)
