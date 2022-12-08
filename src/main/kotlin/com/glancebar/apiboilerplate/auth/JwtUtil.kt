package com.glancebar.apiboilerplate.auth

import com.glancebar.apiboilerplate.dto.UserDTO
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

/**
 * Jwt util class.
 * @author YISEN
 * @date 2021/1/28
 */
@Component
class JwtUtil {

  private val key = Keys.secretKeyFor(SignatureAlgorithm.HS256) // or HS384 or HS512

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
    claims["isCredentialNonExpired"] = user.isCredentialsNonExpired

    return Jwts.builder()
      .setClaims(claims)
      .setExpiration(Date(expiration))
      .signWith(key)
      .compact()
  }

  fun generateToken(user: UserDTO, expiration: Long): String {
    val claims = Jwts.claims().setSubject(user.username)
    claims["username"] = user.username
    claims["isNonExpired"] = true
    claims["isEnabled"] = true
    claims["isNonLocked"] = user.isActive
    claims["isCredentialNonExpired"] = true
    return Jwts.builder()
      .setClaims(claims)
      .setExpiration(Date(expiration))
      .signWith(key)
      .compact()
  }
}
