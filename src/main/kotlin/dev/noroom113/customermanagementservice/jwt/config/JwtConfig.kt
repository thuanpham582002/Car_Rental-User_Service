package dev.noroom113.customermanagementservice.jwt.config

import dev.noroom113.customermanagementservice.jwt.interceptor.AccessControlInterceptor
import dev.noroom113.customermanagementservice.jwt.interceptor.JwtInterceptor
import dev.noroom113.customermanagementservice.jwt.interceptor.TokenExpiryInterceptor
import dev.noroom113.customermanagementservice.jwt.util.JwtUtil
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig {
    @Bean
    fun jwtUtil(): JwtUtil {
        return JwtUtil()
    }

    @Bean(name = ["AccessControlInterceptor"])
    fun accessControlInterceptor(jwtUtil: JwtUtil): JwtInterceptor {
        return AccessControlInterceptor(jwtUtil)
    }

    @Bean(name = ["TokenExpiryInterceptor"])
    fun tokenExpiryInterceptor(jwtUtil: JwtUtil): JwtInterceptor {
        return TokenExpiryInterceptor(jwtUtil)
    }

    @Bean(name = ["interceptors"])
    fun interceptors(
        @Qualifier("AccessControlInterceptor") accessControlInterceptor: JwtInterceptor,
        @Qualifier("TokenExpiryInterceptor") tokenExpiryInterceptor: JwtInterceptor
    ): List<JwtInterceptor> {
        return listOf(accessControlInterceptor, tokenExpiryInterceptor)
    }
}