package com.glancebar.apiboilerplate.repository

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import com.glancebar.apiboilerplate.entity.RoleEntity
import com.glancebar.apiboilerplate.utils.Log
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 *
 * @author YISEN
 * @date 2020/12/18
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RoleRepositoryTest {

  companion object : Log()

  @Autowired
  lateinit var roleRepository: RoleRepository

  @Autowired
  lateinit var authorityRepository: AuthorityRepository

  var roleResult: RoleEntity? = null
  var authorityResult: AuthorityEntity? = null

  @Test
  @Order(1)
  internal fun addAuthority() {
    val authorityEntity = AuthorityEntity(
      name = "TEST_AUTH",
      description = "Test authority"
    )
    authorityResult = authorityRepository.save(authorityEntity)
    assertNotNull(authorityResult)
  }

  @Test
  @Order(2)
  internal fun addRole() {
    val role = RoleEntity(
      name = "USER",
      description = "Normal user role",
      authorities = mutableSetOf(authorityResult!!)
    )

    val result = roleRepository.save(role)

    logger.info("Inserted result $result")

    roleResult = result
    assertNotNull(result)
  }

  @Test
  @Order(3)
  internal fun getRole() {
    val result = roleRepository.findTopByIdEquals(roleResult!!.id!!)
    assertNotNull(result)
  }

  @Test
  @Order(4)
  internal fun getRoles() {
    val results = roleRepository.findAll()
    assertTrue(results.size > 0)
  }

  @Test
  @Order(5)
  internal fun updateRole() {
    roleResult!!.description = "new description"
    val updated = roleRepository.save(roleResult!!)
    assertEquals(roleResult!!.id, updated.id)
    assertEquals(roleResult!!.description, updated.description)
  }

  @Test
  @Order(6)
  internal fun deleteRole() {
    roleRepository.deleteById(roleResult!!.id!!)
    assertNull(roleRepository.findTopByIdEquals(roleResult!!.id!!))
  }

  @Test
  internal fun deleteAuthority() {
    authorityRepository.deleteById(authorityResult!!.id!!)
    assertNull(authorityRepository.findTopByIdEquals(authorityResult!!.id!!))
  }
}
