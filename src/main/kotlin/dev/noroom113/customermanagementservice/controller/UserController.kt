package dev.noroom113.customermanagementservice.controller

import dev.noroom113.customermanagementservice.entities.RegisterRequest
import dev.noroom113.customermanagementservice.entities.User
import dev.noroom113.customermanagementservice.entities.dto.UserDto
import dev.noroom113.customermanagementservice.entities.dto.LoginRequest
import dev.noroom113.customermanagementservice.service.IndetityCardService
import dev.noroom113.customermanagementservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/user")
class UserController(
    private val userService: UserService,
    private val indetityCardService: IndetityCardService,
    private val passwordEncoder: PasswordEncoder
) {
    @GetMapping("/all")
    fun getAllUsers(): MutableList<User> {
        return userService.getAllUsers()
    }

    @PostMapping("/save")
    fun saveUser(@RequestBody request: RegisterRequest): ResponseEntity<UserDto> {
        val user = User(
            identityId = request.identityId,
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password)
        )
        return ResponseEntity.ok(userService.save(user).toUserDto())
    }

    // login
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<UserDto> {
        val indetityCard = indetityCardService.findByIdentityId(request.identityId)
        val user = userService.findByIndentityId(indetityCard?.id ?: -1)
        return if (user != null && passwordEncoder.matches(request.password, user.password)) {
            ResponseEntity.ok(user.toUserDto())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/getUserByUsername/{username}")
    fun getUserByUsername(@PathVariable username: String): ResponseEntity<User?> {
        val user = userService.findByUsername(username)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}