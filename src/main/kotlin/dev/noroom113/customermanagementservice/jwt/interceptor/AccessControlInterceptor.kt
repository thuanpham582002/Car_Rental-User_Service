package dev.noroom113.customermanagementservice.jwt.interceptor

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import dev.noroom113.customermanagementservice.jwt.util.JwtUtil
import dev.noroom113.customermanagementservice.jwt.util.getBearerToken
import dev.noroom113.customermanagementservice.jwt.util.hasAccess
import dev.noroom113.customermanagementservice.entities.UrlAccessable
import io.jsonwebtoken.Claims
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class AccessControlInterceptor(
    private val jwtUtil: JwtUtil
) : JwtInterceptor {
    override fun intercept(request: HttpServletRequest, response: HttpServletResponse) {
        request.getBearerToken()?.let {
            val claims: Claims = jwtUtil.getClaims(it)
            val urlsAccessable: String = claims["urlsAccessable"].toString()
            val setUrlsAccessable = mutableSetOf<UrlAccessable>()
            kotlin.runCatching {
                setUrlsAccessable.addAll(
                    ObjectMapper().readValue(
                        urlsAccessable,
                        object : TypeReference<Set<UrlAccessable>>() {})
                )
            }.onFailure {
                response.sendError(403, "Forbidden")
                throw Exception("Forbidden")
            }
            if (!request.hasAccess(setUrlsAccessable)) {
                response.sendError(403, "Forbidden")
                throw Exception("Forbidden")
            }
        }
            ?: run {
                response.sendError(401, "Unauthorized")
                throw Exception("Token is invalid")
            }
    }
}