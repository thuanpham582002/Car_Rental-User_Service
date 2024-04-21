package dev.noroom113.customermanagementservice.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {
//    @Bean
//    fun authenticationManager(
//        userDetailsService: UserDetailsService,
//        passwordEncoder: PasswordEncoder): AuthenticationManager {
//        val authenticationProvider = DaoAuthenticationProvider()
//        authenticationProvider.setUserDetailsService(userDetailsService)
//        authenticationProvider.setPasswordEncoder(passwordEncoder)
//
//        return ProviderManager(authenticationProvider)
//    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize("/login", permitAll)
                authorize(anyRequest, permitAll)
            }
        }
        return http.build()
    }
}