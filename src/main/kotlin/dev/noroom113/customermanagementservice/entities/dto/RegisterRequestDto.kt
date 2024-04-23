package dev.noroom113.customermanagementservice.entities.dto

data class RegisterRequestDto(
    val id: Long,
    val identityNumber: String,
    val username: String,
    val email: String,
)