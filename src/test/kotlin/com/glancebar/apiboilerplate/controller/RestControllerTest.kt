package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import com.glancebar.apiboilerplate.entity.RoleEntity
import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.service.UserDetailServiceImpl
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.utils.createRequestEntity
import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * Test service with cache should not Mock repository layer
 * @author YISEN
 * @date 2020/12/21
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RestControllerTest(
  @Autowired
  val restTemplate: TestRestTemplate,

  @Autowired
  val passwordEncoder: PasswordEncoder,
) {
//    @MockBean
//    lateinit var userRepository: UserRepository

  @MockBean
  lateinit var userDetailsServiceImpl: UserDetailServiceImpl

  final val username = "ethan"
  final val password = "123456"

  final val authority = AuthorityEntity(
    id = ObjectId(),
    name = "API",
    description = "Normal user authority"
  )

  final val role = RoleEntity(
    id = ObjectId(),
    name = "ROLE_USER",
    description = "Normal user role.",
    authorities = mutableSetOf(authority)
  )

  final val queryResult = UserEntity(
    id = ObjectId(),
    username = username,
    password = ""
  )

  /**
   * Get all authorities of user
   */
  private fun getAuthorities(userEntity: UserEntity): List<GrantedAuthority> {
    val authorities: MutableSet<GrantedAuthority> = mutableSetOf()
    userEntity.roles.forEach { roleEntity: RoleEntity ->
      authorities.add(SimpleGrantedAuthority(roleEntity.name))
      roleEntity.authorities.forEach { authorityEntity ->
        authorities.add(SimpleGrantedAuthority(authorityEntity.name))
      }
    }
    return authorities.toList()
  }

  /**
   *  @BeforeAll method  must be static unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS).
   *
   */
  @BeforeAll
  internal fun setUp() {
    println(">>>SetUp")
  }

  @Test
  internal fun `Test unauthorized user access`() {
    val responseEntity = restTemplate.getForEntity<Any>("/hello")
    assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.UNAUTHORIZED)
  }

  @Test
  internal fun `Test authenticated but with out authority access`() {
    // set up user password
    queryResult.password = passwordEncoder.encode(password)
    queryResult.roles = mutableSetOf()

    Mockito.`when`(userDetailsServiceImpl.loadUserByUsername(username)).thenReturn(
      User(
        queryResult.username,
        queryResult.password,
        queryResult.isActive,
        true,
        true,
        !queryResult.isDelete,
        getAuthorities(queryResult)
      )
    )

    val responseEntity = restTemplate.exchange(
      "/hello",
      HttpMethod.GET,
      createRequestEntity(username, password),
      OkResult::class.java
    )

    assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.FORBIDDEN)
  }

  @Test
  internal fun `Test with authority access`() {
    queryResult.password = passwordEncoder.encode(password)
    queryResult.roles = mutableSetOf(role)

    Mockito.`when`(userDetailsServiceImpl.loadUserByUsername(username)).thenReturn(
      User(
        queryResult.username,
        queryResult.password,
        queryResult.isActive,
        true,
        true,
        !queryResult.isDelete,
        getAuthorities(queryResult)
      )
    )

    val responseEntity = restTemplate.exchange(
      "/hello",
      HttpMethod.GET,
      createRequestEntity(username, password),
      OkResult::class.java
    )

    assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
    assertThat(responseEntity.body).isEqualTo(OkResult())
  }

  @AfterAll
  internal fun tearDown() {
    println(">>>TearDown")
  }
}
