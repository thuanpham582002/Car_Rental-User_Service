package dev.noroom113.customermanagementservice.jwt

import dev.noroom113.customermanagementservice.entities.UrlAccessable
import dev.noroom113.customermanagementservice.jwt.interceptor.JwtInterceptor
import dev.noroom113.customermanagementservice.jwt.util.JwtUtil
import dev.noroom113.customermanagementservice.jwt.util.getBearerToken
import dev.noroom113.customermanagementservice.jwt.util.hasAccess
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val interceptors: List<JwtInterceptor>,
) : OncePerRequestFilter() {
    companion object{
        val WHITE_LIST = setOf<UrlAccessable>(
            UrlAccessable("/swagger-resources/**"),
            UrlAccessable("/swagger-ui.html/**"),
            UrlAccessable("/swagger-resources/**"),
            UrlAccessable("/swagger-ui/**"),
            UrlAccessable("/v3/api-docs/**"),
            UrlAccessable("/api/v1/user/login"),
            UrlAccessable("/api/v1/user/save"),
        )
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        if (request.hasAccess(WHITE_LIST)) {
            filterChain.doFilter(request, response)
            return
        }
        for (interceptor in interceptors) {
            interceptor.intercept(request, response)
        }
        request.getBearerToken()?.let {
            response.setHeader("Authorization", jwtUtil.refreshToken(it))
        }
        filterChain.doFilter(request, response)
    }
}