package dev.noroom113.customermanagementservice.security.config

import dev.noroom113.customermanagementservice.exception.BaseException
import dev.noroom113.customermanagementservice.service.UserService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class UserDetailsServiceImpl(
    private val userService: UserService
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) throw BaseException.UserNameNotFoundException()
        val customer = userService.findByUsername(username) ?: throw BaseException.UserNameNotFoundException()
        val authorities: MutableList<GrantedAuthority> = ArrayList()
        customer.accessibilitys.forEach { authorities.add(SimpleGrantedAuthority(it.name)) }
        return org.springframework.security.core.userdetails.User(customer.userName, customer.password, authorities)
    }
}