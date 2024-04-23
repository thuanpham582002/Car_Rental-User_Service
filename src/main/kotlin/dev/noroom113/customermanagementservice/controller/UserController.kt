package dev.noroom113.customermanagementservice.controller

import dev.noroom113.customermanagementservice.client.AuthServiceClient
import dev.noroom113.customermanagementservice.entities.DriverLicense
import dev.noroom113.customermanagementservice.entities.RegisterRequest
import dev.noroom113.customermanagementservice.entities.User
import dev.noroom113.customermanagementservice.entities.dto.LoginRequest
import dev.noroom113.customermanagementservice.entities.dto.UserDto
import dev.noroom113.customermanagementservice.jwt.util.JwtUtil
import dev.noroom113.customermanagementservice.jwt.util.getBearerToken
import dev.noroom113.customermanagementservice.service.IndetityCardService
import dev.noroom113.customermanagementservice.service.UserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.Date

@RestController
@RequestMapping("api/v1/user")
class UserController(
    private val userService: UserService,
    private val indetityCardService: IndetityCardService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
    private val authServiceClient: AuthServiceClient
) {
    @GetMapping("/all")
    fun getAllUsers(): MutableList<User> {
        return userService.getAllUsers()
    }

    @PostMapping("/save")
    fun saveUser(@RequestBody request: RegisterRequest): ResponseEntity<UserDto> {
        val user = User(
            identityId = request.identityNumber,
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            // 1 is id guest accessibility
            accessibilityIds = listOf(1)
        )
        return ResponseEntity.ok(userService.save(user).toUserDto())
    }

    @PutMapping("/update")
    fun updateUser(
        @RequestBody userDto: UserDto,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse,
    ): ResponseEntity<UserDto> {
        httpServletRequest.getBearerToken()?.let { token ->
            if (jwtUtil.getClaims(token).subject != userDto.indentityCard.number) {
                throw Exception("You cannot update other user's information")
            }
            val user = userService.findById(userDto.id) ?: return ResponseEntity.notFound().build()

            if (user.accessibilityIds != userDto.accessibilityIds) {
                throw Exception("Accessibility cannot be changed by yourself")
            }
            if (user.driverLicense != null && userDto.driverLicense == null) {
                throw Exception("Driver license cannot be removed")
            }
            userService.update(
                user.copy(
                    driverLicense = userDto.driverLicense?.let {
                        DriverLicense(
                            id = user.driverLicense?.id ?: 0,
                            number = it.number,
                            created_at = user.driverLicense?.created_at ?: Date(System.currentTimeMillis()),
                            updated_at = Date(System.currentTimeMillis())
                        )
                    },
                    updated_at = Date(System.currentTimeMillis()),
                    accessibilityIds = userDto.accessibilityIds.toMutableList().apply {
                        if (userDto.driverLicense != null && user.driverLicense == null) add(3)
                    },
                    email = userDto.email,
                    name = userDto.name
                )
            )
            if (userDto.driverLicense != null && user.driverLicense == null) {
                authServiceClient.login(LoginRequest(user.indentityCard.number, passwordEncoder.encode(user.password)))
            }
            return ResponseEntity.ok(user.toUserDto())
        } ?: return ResponseEntity.notFound().build()

    }

    // login
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<UserDto> {
        val indetityCard = indetityCardService.findByIdentityNumber(request.identityNumber)
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

    @GetMapping("/getUserByIndentityCardName/{indentityCardName}")
    fun getUserByIndentityCardName(
        @PathVariable indentityCardName: String,
        httpServletRequest: HttpServletRequest,
    ): ResponseEntity<UserDto?> {
        httpServletRequest.getBearerToken()?.let { token ->
            if (jwtUtil.getClaims(token).subject != indentityCardName) {
                throw Exception("You cannot get other user's information")
            }
            val user =
                userService.findByIndentityId(indetityCardService.findByIdentityNumber(indentityCardName)?.id ?: -1)
            return if (user != null) {
                ResponseEntity.ok(user.toUserDto())
            } else {
                ResponseEntity.notFound().build()
            }
        }
        return ResponseEntity.notFound().build()
    }
}