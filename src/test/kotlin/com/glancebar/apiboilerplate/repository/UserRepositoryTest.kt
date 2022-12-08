package com.glancebar.apiboilerplate.repository

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import com.glancebar.apiboilerplate.entity.GenderEnum
import com.glancebar.apiboilerplate.entity.RoleEntity
import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.utils.Log
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate

/**
 *
 * @author YISEN
 * @date 2020/12/18
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

  @Autowired
  lateinit var userRepository: UserRepository

  @Autowired
  lateinit var passwordEncoder: PasswordEncoder

  @Autowired
  lateinit var roleRepository: RoleRepository

  @Autowired
  lateinit var authorityRepository: AuthorityRepository

  companion object : Log()

  var roleResult: RoleEntity? = null
  var authorityResult: AuthorityEntity? = null
  var userResult: UserEntity? = null

  @Test
  @Order(1)
  internal fun addAuthority() {
    val authority = AuthorityEntity(
      name = "TEST_AUTH",
      description = "This is authority"
    )
    authorityResult = authorityRepository.save(authority)
    assertNotNull(authorityResult)
  }

  @Test
  @Order(2)
  internal fun addRole() {
    val role = RoleEntity(
      name = "ROLE_ROLE",
      description = "This is a role",
      authorities = mutableSetOf(authorityResult!!)
    )
    roleResult = roleRepository.save(role)
    assertNotNull(roleResult)
  }

  @Test
  @Order(3)
  internal fun addUser() {
    val user = UserEntity(
      username = "test",
      password = passwordEncoder.encode("123456"),
      birthday = LocalDate.now(),
      gender = GenderEnum.MALE,
      roles = mutableSetOf(roleResult!!),
      authorities = mutableSetOf(authorityResult!!)
    )

    userResult = userRepository.save(user)
    assertNotNull(userResult)
  }

  @Test
  @Order(4)
  internal fun getUser() {
    val result = userRepository.findTopById(userResult!!.id!!)
    assertNotNull(result)
  }

  @Test
  @Order(5)
  internal fun getUsers() {
    val results = userRepository.findAll()
    assertTrue(results.size > 0)
  }

  @Test
  @Order(6)
  internal fun updateUser() {
    userResult!!.username = "hello"
    val result = userRepository.save(userResult!!)
    assertEquals(userResult!!.id, result.id)
    assertEquals(userResult!!.username, result.username)
  }

  @Test
  @Order(7)
  internal fun deleteUser() {
    userRepository.deleteById(userResult!!.id!!)
    assertNull(userRepository.findTopByIdEquals(userResult!!.id!!))
  }

  @Test
  internal fun deleteRoleAndAuthority() {
    roleRepository.deleteById(roleResult!!.id!!)
    authorityRepository.deleteById(authorityResult!!.id!!)
  }
}
