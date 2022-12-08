package com.glancebar.apiboilerplate.repository

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 *  https://www.baeldung.com/junit-5-test-order
 * @author YISEN
 * @date 2020/12/18
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthorityRepositoryTest {
  @Autowired
  lateinit var authorityRepository: AuthorityRepository

  var authorityResult: AuthorityEntity? = null

  @BeforeAll
  internal fun setUpFixture() {
  }

  @Test
  @Order(1)
  internal fun addAuthority() {
    val authorityEntity = AuthorityEntity(
      name = "TEST_AUTH",
      description = "This is a test authority."
    )

    val result = authorityRepository.save(authorityEntity)

    authorityResult = result
    assumeTrue(result.id != null)
    assertNotNull(result.id)
  }

  @Test
  @Order(2)
  internal fun getAuthority() {
    val result = authorityRepository.findTopByIdEquals(authorityResult!!.id!!)
    assertNotNull(result)
  }

  @Test
  @Order(3)
  internal fun getAuthorities() {
    val authorities = authorityRepository.findAll()
    assertTrue(authorities.size > 0)
  }

  @Test
  @Order(4)
  internal fun updateAuthority() {
    authorityResult!!.description = "new description"
    val updated = authorityRepository.save(authorityResult!!)
    assertEquals(authorityResult!!.id, updated.id)
    assertEquals(authorityResult!!.description, updated.description)
  }

  @Test
  @Order(5)
  internal fun deleteAuthorities() {
    authorityRepository.deleteById(authorityResult!!.id!!)
    assertNull(authorityRepository.findTopByIdEquals(authorityResult!!.id!!))
  }
}
