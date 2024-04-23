package dev.noroom113.customermanagementservice.entities

data class RegisterRequest(
    val identityNumber: String,
    val username: String,
    val email: String,
    val password: String,
)
