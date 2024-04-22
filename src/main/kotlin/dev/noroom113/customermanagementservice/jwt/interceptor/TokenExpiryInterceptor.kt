package dev.noroom113.customermanagementservice.jwt.interceptor

import dev.noroom113.customermanagementservice.jwt.util.JwtUtil
import dev.noroom113.customermanagementservice.jwt.util.getBearerToken
import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class TokenExpiryInterceptor(private val jwtUtil: JwtUtil) : JwtInterceptor {
    override fun intercept(request: HttpServletRequest, response: HttpServletResponse) {
        val token: String? = request.getBearerToken()
        token?.let {
            val claims: Claims = jwtUtil.getClaims(it)
            if (claims.expiration.before(java.util.Date())) {
                response.sendError(401, "Unauthorized")
                throw Exception("Token is expired")
            }
        } ?: run {
            response.sendError(401, "Unauthorized")
            throw Exception("Token is invalid")
        }
    }
}