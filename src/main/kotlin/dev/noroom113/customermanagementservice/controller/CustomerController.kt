package dev.noroom113.customermanagementservice.controller

import dev.noroom113.customermanagementservice.service.UserService
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
    private val userService: UserService
) {

}