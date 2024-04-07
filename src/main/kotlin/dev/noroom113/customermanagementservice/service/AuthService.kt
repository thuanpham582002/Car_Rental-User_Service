package dev.noroom113.customermanagementservice.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Service


@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userService: UserService
) {

}
