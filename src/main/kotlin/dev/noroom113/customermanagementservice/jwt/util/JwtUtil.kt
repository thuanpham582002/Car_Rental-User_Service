package dev.noroom113.customermanagementservice.jwt.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.*

class JwtUtil {
    private val signKey: Key
        get() {
            val keyBytes: ByteArray = Decoders.BASE64.decode(SECRET)
            return Keys.hmacShaKeyFor(keyBytes)
        }

    fun getClaims(token: String?): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(signKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
    }

    fun refreshToken(token: String): String {
        val claims = getClaims(token)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(claims.expiration)
            .signWith(signKey)
            .compact()
    }

    companion object {
        const val SECRET: String = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"
    }
}