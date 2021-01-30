package com.glancebar.apiboilerplate.auth

import com.glancebar.apiboilerplate.config.SysProperties
import com.glancebar.apiboilerplate.utils.logger
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*


/**
 * Jwt util class.
 * @author Ethan Gary
 * @date 2021/1/28
 */
@Component
class JwtUtil(
    val sysProperties: SysProperties
) {

    private val key = Keys.secretKeyFor(SignatureAlgorithm.HS256) //or HS384 or HS512

    /**
     * Given an token, parse and return the username inside it.
     */
    fun parseToken(token: String): String? {
        try {
            val body = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
            return body["username"] as String?
        } catch (e: JwtException) {
            throw e
        }
    }


    /**
     * Generate token by current authenticated user.
     */
    fun generateToken(user: UserDetails, expiration: Long): String {
        val claims = Jwts.claims().setSubject(user.username)
        claims["username"] = user.username
        claims["isNonExpired"] = user.isAccountNonExpired
        claims["isEnabled"] = user.isEnabled
        claims["isNonLocked"] = user.isAccountNonLocked
        claims["isCredentialNoneExpired"] = user.isCredentialsNonExpired

        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(Date(expiration))
            .signWith(key)
            .compact()
    }
}

fun main() {

    val jwtUtil = JwtUtil(SysProperties("", "", 10))
    val user = User.builder()
        .username("username")
        .password("123456")
        .disabled(false)
        .authorities(listOf())
        .credentialsExpired(false)
        .accountLocked(false)
        .build()

    val token = jwtUtil.generateToken(user, 1)
    Thread.sleep(1000)

    println(token)

    println(jwtUtil.parseToken(token))
}