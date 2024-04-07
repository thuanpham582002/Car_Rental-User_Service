package dev.noroom113.customermanagementservice.entities.dto

import dev.noroom113.customermanagementservice.entities.User
import org.springframework.security.crypto.password.PasswordEncoder

data class UserRegisterRequestDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val userName: String,
    val password: String
){
    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            userName = this.userName,
            password = passwordEncoder.encode(this.password),
            accessibilitys = emptyList()
        )
    }
}
