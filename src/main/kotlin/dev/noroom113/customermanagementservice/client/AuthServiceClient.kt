package dev.noroom113.customermanagementservice.client

import dev.noroom113.customermanagementservice.entities.dto.LoginRequest
import dev.noroom113.customermanagementservice.entities.dto.TokenDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "auth-service", path = "/api/v1/auth", configuration = [CustomRequestInterceptor::class])
interface AuthServiceClient {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<TokenDto>
}