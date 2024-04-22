package dev.noroom113.customermanagementservice.entities.dto

import java.io.Serializable

data class LoginRequest(
    val identityId: String,
    val password: String,
) : Serializable
