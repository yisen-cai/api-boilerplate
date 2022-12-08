package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.entity.GenderEnum
import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.repository.UserRepository
import com.glancebar.apiboilerplate.utils.createRequestHeaders
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.Instant
import java.time.ZoneId

/**
 * https://spring.io/guides/gs/testing-web/
 */
@SpringBootTest
@AutoConfigureMockMvc
internal class AuthControllerTest {

  @MockBean
  lateinit var userRepository: UserRepository

  @Autowired
  lateinit var authController: AuthController

  @Autowired
  lateinit var passwordEncoder: PasswordEncoder

  @Autowired
  lateinit var mockMvc: MockMvc

  @Test
  fun registerUserConflict() {
//        val textPlainUtf8 = MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"))
    val user =
      """
            {
                "username": "yisen",
                "password": "123456",
                "birthday": "1608043996006",
                "gender": "male"
            }
      """.trimIndent()

    `when`(userRepository.existsByUsernameEquals("yisen")).thenReturn(true)

    mockMvc.perform(
      MockMvcRequestBuilders.post("/auth/register")
        .content(user)
        .contentType(MediaType.APPLICATION_JSON)
    )
      .andExpect(MockMvcResultMatchers.status().isBadRequest)
      .andExpect(
        MockMvcResultMatchers.content()
          .contentType(MediaType.APPLICATION_JSON)
      )
  }

  @Test
  fun registerUserSuccess() {
    val username = "hello"
    val birthday = 1608043996006
    val gender = "male"
    val password = "123456"
    val user =
      """
            {
                "username": "$username",
                "password": "$password",
                "birthday": $birthday,
                "gender": "$gender"
            }
      """.trimIndent()

    val userEntity = UserEntity(
      username = username,
      password = passwordEncoder.encode(password),
      birthday = Instant.ofEpochMilli(birthday).atZone(ZoneId.systemDefault()).toLocalDate(),
      gender = GenderEnum.convert(gender)
    )

    `when`(userRepository.existsByUsernameEquals(username)).thenReturn(false)
    // will not work. Because user.id is created over service layer, but this mocked user id are not equal
    // service layer.
    `when`(userRepository.save(userEntity)).thenReturn(userEntity)

    mockMvc.perform(
      MockMvcRequestBuilders.post("/auth/register")
        .content(user)
        .contentType(MediaType.APPLICATION_JSON)
    )
      .andExpect(MockMvcResultMatchers.status().isCreated)
      .andExpect(
        MockMvcResultMatchers.content()
          .contentType(MediaType.APPLICATION_JSON)
      )
  }

  @Test
  fun logout() {
  }

  @Test
  fun login() {
    val username = "username"
    val originPassword = "password"
    val password = passwordEncoder.encode(originPassword)

    val userEntity = UserEntity(
      username = username,
      password = password
    )

    `when`(userRepository.findTopByUsernameEquals(username)).thenReturn(userEntity)

    mockMvc.perform(
      MockMvcRequestBuilders.post("/auth/login")
        .headers(createRequestHeaders(username, originPassword))
    )
      .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
      .andExpect(
        MockMvcResultMatchers.content()
          .contentType(MediaType.APPLICATION_JSON)
      )
      .andExpect(
        MockMvcResultMatchers.jsonPath("$.accessToken").exists()
      )
  }

  @Test
  fun getUserService() {
  }
}
