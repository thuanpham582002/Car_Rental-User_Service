package dev.noroom113.customermanagementservice.entities.dto

import java.io.Serializable

data class LoginRequest(
    val identityNumber: String,
    val password: String,
) : Serializable
