package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.service.RoleService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 *
 * @author YISEN
 * @date 2020/12/18
 */
@SpringBootTest
@AutoConfigureMockMvc
internal class RoleControllerTest {

  @Autowired
  lateinit var roleController: RoleController

  @MockBean
  lateinit var roleService: RoleService

  @Autowired
  lateinit var mockMvc: MockMvc

//    @Test
  fun addRole() {
    val body =
      """
            {
                name: "USER",
                description: "Normal user role.",
                authorities: []
            }
      """.trimIndent()

    mockMvc.perform(
      MockMvcRequestBuilders.post("/roles")
        .content(body)
        .contentType(MediaType.APPLICATION_JSON)
    )
      .andExpect(MockMvcResultMatchers.status().isCreated)
      .andExpect(
        MockMvcResultMatchers
          .content()
          .contentType(MediaType.APPLICATION_JSON)
      )
  }

//    @Test
  fun getRole() {
    HttpStatus.CREATED
  }

  @Test
  fun getRoles() {
  }

  @Test
  fun deleteRole() {
  }

  @Test
  fun updateRole() {
  }
}
