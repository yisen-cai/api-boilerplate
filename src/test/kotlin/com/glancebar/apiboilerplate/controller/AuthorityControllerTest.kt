package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.service.AuthorityService
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
@SpringBootTest
@AutoConfigureMockMvc
internal class AuthorityControllerTest {

  @Autowired
  lateinit var authorityController: AuthorityController

  @MockBean
  lateinit var authorityService: AuthorityService

  @Autowired
  lateinit var mockMvc: MockMvc

  @Test
  @Disabled("")
  fun addAuthority() {
    val body =
      """
                {
                    "name": "API",
                    "description: "Basic user authority of normal user."
                }
      """.trimIndent()

    mockMvc.perform(
      MockMvcRequestBuilders.post("/authorities")
        .contentType(body)
        .contentType(MediaType.APPLICATION_JSON)
    )
      .andExpect(
        MockMvcResultMatchers.status().isCreated
      )
      .andExpect(
        MockMvcResultMatchers
          .content()
          .contentType(MediaType.APPLICATION_JSON)
      )
  }

  @Test
  fun getAuthority() {
  }

  @Test
  fun getAuthorities() {
  }

  @Test
  fun updateAuthorities() {
  }

  @Test
  fun deleteAuthorities() {
  }
}
