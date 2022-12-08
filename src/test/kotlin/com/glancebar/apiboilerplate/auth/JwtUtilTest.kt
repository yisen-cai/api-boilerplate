package com.glancebar.apiboilerplate.auth

import org.junit.jupiter.api.*
import org.springframework.security.core.userdetails.User

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
internal class JwtUtilTest {

  private lateinit var jwtToken: String

  private val jwtUtil = JwtUtil()

  @Test
  @Order(1)
  fun generateToken() {
    val user = User.builder()
      .username("username")
      .password("123456")
      .disabled(false)
      .authorities(listOf())
      .credentialsExpired(false)
      .accountLocked(false)
      .build()

    val token = jwtUtil.generateToken(user, System.currentTimeMillis() + 60000)

    println(token)

    jwtToken = token

    Assertions.assertNotNull(token)
  }

  @Test
  @Order(2)
  fun parseToken() {
    val user = User.builder()
      .username("username")
      .password("123456")
      .disabled(false)
      .authorities(listOf())
      .credentialsExpired(false)
      .accountLocked(false)
      .build()

    val token = jwtUtil.generateToken(user, System.currentTimeMillis() + 60000)

    val username = jwtUtil.parseToken(token)
    Assertions.assertNotNull(username)
  }
}
