package dev.noroom113.customermanagementservice.entities

data class RegisterRequest(
    val identityId: String,
    val username: String,
    val email: String,
    val password: String,
){
    fun toUser(): User {
        return User(
            identityId = identityId,
            username = username,
            email = email,
            password = password
        )
    }
}
