package dev.noroom113.customermanagementservice.service

import dev.noroom113.customermanagementservice.entities.User
import dev.noroom113.customermanagementservice.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun findByUsername(username: String): User? {
        return userRepository.findUserByName(username)
    }

    fun findByIndentityId(identityId: Long): User? {
        return userRepository.findUserByIndentityCardId(identityId)
    }

    fun findById(id: Long): User? {
        return userRepository.findUserById(id)
    }

    fun existsByUsername(username: String): Boolean {
        return userRepository.findUserByName(username) != null
    }

    fun save(user: User): User {
        userRepository.save(user)
        return user
    }

    fun update(user: User): User {
        userRepository.save(user)
        return user
    }

    fun getAllUsers(): MutableList<User> {
        return userRepository.findAll()
    }
}
