package dev.noroom113.customermanagementservice.entities.dto

import dev.noroom113.customermanagementservice.entities.User
import org.springframework.security.crypto.password.PasswordEncoder

data class UserLoginRequestDto(
    val username: String,
    val password: String
)
