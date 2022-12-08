package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.entity.RoleEntity
import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.repository.UserRepository
import com.glancebar.apiboilerplate.utils.AuthResult
import com.glancebar.apiboilerplate.utils.createRequestEntity
import io.jsonwebtoken.lang.Assert
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
internal class HelloControllerTest {

  @Autowired
  lateinit var restTemplate: TestRestTemplate

  @MockBean
  lateinit var userRepository: UserRepository

  @Autowired
  lateinit var mockMvc: MockMvc

  @Autowired
  lateinit var passwordEncoder: PasswordEncoder

  private lateinit var accessToken: String

  val username = "username"
  val originPassword = "password"

  lateinit var password: String

  lateinit var userEntity: UserEntity

  @BeforeAll
  fun setUp() {
    password = passwordEncoder.encode(originPassword)
    userEntity = UserEntity(
      username = username,
      password = password,
      roles = mutableSetOf(
        RoleEntity(name = "ROLE_USER", description = "")
      )
    )
    val headers = HttpHeaders()
    headers["Authorization"] = "Basic aGVsbG8xOjEyMzQ1Ng=="
    Mockito.`when`(userRepository.findTopByUsernameEquals(username)).thenReturn(userEntity)
    val responseEntity: ResponseEntity<AuthResult> = restTemplate.exchange(
      "/auth/login",
      HttpMethod.POST,
      createRequestEntity(username, originPassword),
      AuthResult::class.java
    )

    Assert.notNull(responseEntity.body!!.accessToken)
    this.accessToken = responseEntity.body!!.accessToken
  }

  @Test
  internal fun hello() {

    Mockito.`when`(userRepository.findTopByUsernameEquals(username)).thenReturn(userEntity)

    val headers = HttpHeaders()
    headers["Authorization"] = "Bearer $accessToken"
    mockMvc.perform(
      MockMvcRequestBuilders.get("/hello")
        .headers(headers)
    )
      .andExpect(
        MockMvcResultMatchers.status().is2xxSuccessful
      )
      .andExpect(
        MockMvcResultMatchers.content()
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.msg").exists()
      )
  }
}
