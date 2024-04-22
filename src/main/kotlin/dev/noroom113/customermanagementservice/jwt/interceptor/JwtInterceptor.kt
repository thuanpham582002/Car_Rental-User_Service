package dev.noroom113.customermanagementservice.jwt.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

interface JwtInterceptor {
    fun intercept(request: HttpServletRequest, response: HttpServletResponse)
}
