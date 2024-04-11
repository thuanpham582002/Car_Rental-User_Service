package dev.noroom113.customermanagementservice.controller

import dev.noroom113.customermanagementservice.entities.User
import dev.noroom113.customermanagementservice.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/customer")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/all")
    fun getAllUsers(): MutableList<User> {
        return userService.getAllUsers()
    }
}