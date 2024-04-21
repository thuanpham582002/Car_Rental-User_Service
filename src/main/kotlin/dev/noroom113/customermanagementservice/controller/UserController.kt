package dev.noroom113.customermanagementservice.controller

import dev.noroom113.customermanagementservice.entities.User
import dev.noroom113.customermanagementservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/all")
    fun getAllUsers(): MutableList<User> {
        return userService.getAllUsers()
    }

//    @PostMapping("/save")
//    fun saveUser(@RequestBody request: RegisterRequest): ResponseEntity<RegisterRequestDto> {
////        val user = User(
////            identityId = request.identityId,
////            username = request.username,
////            email = request.email,
////            password = request.password
////        )
////        return ResponseEntity.ok(userService.save(user))
//    }

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