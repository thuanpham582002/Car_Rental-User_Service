package dev.noroom113.customermanagementservice.controller

import dev.noroom113.customermanagementservice.entities.dto.UserLoginRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class LoginController(val authenticationManager: AuthenticationManager) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: UserLoginRequestDto): ResponseEntity<Void> {
        val authenticationRequest =
            UsernamePasswordAuthenticationToken.unauthenticated(
                loginRequest.username, loginRequest.password
            )
        authenticationManager.authenticate(authenticationRequest)
        return ResponseEntity.ok().build()
    }
}