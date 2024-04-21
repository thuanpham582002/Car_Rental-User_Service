package dev.noroom113.customermanagementservice.service

import dev.noroom113.customermanagementservice.entities.User
import dev.noroom113.customermanagementservice.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun findByUsername(username: String): User? {
        return userRepository.findCustomerByUserName(username)
    }

    fun existsByUsername(username: String): Boolean {
        return userRepository.findCustomerByUserName(username) != null
    }

//    fun save(registerRequest: RegisterRequest): User {
//        userRepository.save(
//            User(
//                identityId = registerRequest.identityId,
//                username = registerRequest.username,
//                email = registerRequest.email,
//                password = registerRequest.password
//            )
//        )
//        return user
//    }

    fun getAllUsers(): MutableList<User> {
        return userRepository.findAll()
    }
}
