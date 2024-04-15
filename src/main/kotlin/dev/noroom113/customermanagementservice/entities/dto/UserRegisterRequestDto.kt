package dev.noroom113.customermanagementservice.entities.dto

import dev.noroom113.customermanagementservice.entities.IndentityCard
import dev.noroom113.customermanagementservice.entities.User
import org.springframework.security.crypto.password.PasswordEncoder

data class UserRegisterRequestDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val userName: String,
    val password: String
) {
    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(
            name = "$firstName $lastName",
            email = email,
            userName = userName,
            password = passwordEncoder.encode(password),
            accessibilitys = emptyList(),
            indentityCard = IndentityCard(
                number = "",
                imageBase64 = "",
                created_at = java.sql.Date(System.currentTimeMillis()),
                updated_at = java.sql.Date(System.currentTimeMillis())
            ),
            driverLicense = null,
            created_at = java.sql.Date(System.currentTimeMillis()),
            updated_at = java.sql.Date(System.currentTimeMillis())
        )
    }
}
