package dev.noroom113.customermanagementservice.controller

import dev.noroom113.customermanagementservice.entities.dto.UserRegisterRequestDto
import dev.noroom113.customermanagementservice.exception.BaseException
import dev.noroom113.customermanagementservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class RegisterController(
    private val passwordEncoder: PasswordEncoder,
    private val userService: UserService
) {

    @PostMapping("register")
    fun register(@RequestBody registerRequest: UserRegisterRequestDto): ResponseEntity<Void> {
        if (userService.existsByUsername(registerRequest.userName)) {
            throw BaseException.UserExistsException()
        }
        userService.save(registerRequest.toUser(passwordEncoder))
        return ResponseEntity.ok().build()
    }
}