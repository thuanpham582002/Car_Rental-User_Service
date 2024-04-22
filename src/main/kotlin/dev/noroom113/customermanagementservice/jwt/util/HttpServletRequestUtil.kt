package dev.noroom113.customermanagementservice.jwt.util

import dev.noroom113.customermanagementservice.entities.HttpMethod
import dev.noroom113.customermanagementservice.entities.UrlAccessable
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.util.StringUtils
import java.util.concurrent.atomic.AtomicBoolean

fun HttpServletRequest.getBearerToken(): String? {
    val token = getHeader("Authorization")
    return if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
        token.substring(7)
    } else {
        null
    }
}

fun HttpServletRequest.hasAccess(setUrlsAccessable: Set<UrlAccessable>): Boolean {
    val isAccess = AtomicBoolean(false)
    setUrlsAccessable.forEach {
        val matcher = if (it.method == HttpMethod.ALL) {
            AntPathRequestMatcher(it.uri)
        } else {
            AntPathRequestMatcher(it.uri, it.method.name)
        }
        if (matcher.matches(this)) {
            isAccess.set(true)
        }
    }
    return isAccess.get()
}