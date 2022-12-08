package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import com.glancebar.apiboilerplate.exceptions.NotFoundException
import com.glancebar.apiboilerplate.exceptions.ParamsException
import com.glancebar.apiboilerplate.repository.AuthorityRepository
import com.glancebar.apiboilerplate.vo.AuthorityVO
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus

/**
 *
 * @author YISEN
 * @date 2020/12/19
 */
@SpringBootTest
class AuthorityServiceImplTest {

  @Autowired
  lateinit var authorityService: AuthorityService

  @MockBean
  lateinit var authorityRepository: AuthorityRepository

  final val authorityName = "TEST_NAME"
  final val authorityDescription = "Test authority description"

  val authorityVO = AuthorityVO()

  final val authorityEntity = AuthorityEntity(
    id = ObjectId(),
    name = authorityName,
    description = authorityDescription
  )

  @Test
  internal fun addAuthority() {
    authorityVO.name = authorityName
    authorityVO.description = authorityDescription
    val before = AuthorityEntity(
      name = authorityName,
      description = authorityDescription
    )

    Mockito.`when`(authorityRepository.existsByNameEquals(authorityName)).thenReturn(false)
    Mockito.`when`(authorityRepository.save(before)).thenReturn(authorityEntity)

    val result = authorityService.addAuthority(authorityVO)

    assertEquals(HttpStatus.CREATED, result.statusCode)
  }

  @Test
  internal fun addExistsAuthority() {
    authorityVO.name = authorityName
    authorityVO.description = authorityDescription
    Mockito.`when`(authorityRepository.existsByNameEquals(authorityName)).thenReturn(true)
    assertThrows(ParamsException::class.java) {
      authorityService.addAuthority(authorityVO)
    }
  }

  @Test
  internal fun getAuthority() {
    Mockito.`when`(authorityRepository.findTopByIdEquals(authorityEntity.id!!)).thenReturn(authorityEntity)
    val result = authorityService.getAuthority(authorityEntity.id.toString())
    assertEquals(authorityEntity, result.body)
  }

  @Test
  internal fun getAuthorityWithInvalidObjectId() {
    val invalidObjectId = "123"
    assertThrows(ParamsException::class.java) {
      authorityService.getAuthority(invalidObjectId)
    }
  }

  @Test
  internal fun getAuthorities() {
    val pageable = PageRequest.of(0, 10)
    Mockito.`when`(authorityRepository.findAll(pageable)).thenReturn(PageImpl(listOf(authorityEntity)))
    val results = authorityService.getAuthorities(pageable)
    assertTrue(results.body!!.isNotEmpty())
  }

  @Test
  internal fun updateAuthority() {
    authorityVO.id = authorityEntity.id.toString()
    authorityVO.name = "NEW_NAME"
    authorityVO.description = authorityDescription

    val before = AuthorityEntity(
      id = authorityEntity.id,
      name = authorityVO.name,
      description = authorityDescription
    )

    Mockito.`when`(authorityRepository.existsById(authorityEntity.id!!)).thenReturn(true)
    Mockito.`when`(authorityRepository.existsByNameEquals(before.name)).thenReturn(false)
    Mockito.`when`(authorityRepository.save(before)).thenReturn(authorityEntity)

    val result = authorityService.updateAuthority(authorityVO)

    assertEquals(HttpStatus.RESET_CONTENT, result.statusCode)
  }

  @Test
  internal fun updateWithExistsName() {
    authorityVO.id = authorityEntity.id.toString()
    authorityVO.name = "NEW_NAME"
    authorityVO.description = authorityDescription

    Mockito.`when`(authorityRepository.existsById(authorityEntity.id!!)).thenReturn(true)
    Mockito.`when`(authorityRepository.existsByNameEquals(authorityVO.name)).thenReturn(true)

    assertThrows(ParamsException::class.java) {
      authorityService.updateAuthority(authorityVO)
    }
  }

  @Test
  internal fun updateAuthorityWithNotExistsId() {
    authorityVO.id = ObjectId().toString()
    authorityVO.name = "NEW_NAME"
    authorityVO.description = authorityDescription

    Mockito.`when`(authorityRepository.existsById(authorityEntity.id!!)).thenReturn(false)

    assertThrows(NotFoundException::class.java) {
      authorityService.updateAuthority(authorityVO)
    }
  }

  @Test
  internal fun deleteAuthority() {
    val repository = mock(AuthorityRepository::class.java)
    Mockito.`when`(authorityRepository.existsById(authorityEntity.id!!)).thenReturn(true)
    Mockito.doNothing().`when`(repository).deleteById(authorityEntity.id!!)
    val result = authorityService.deleteAuthority(authorityEntity.id.toString())
    assertEquals(HttpStatus.NO_CONTENT, result.statusCode)
  }

  @Test
  internal fun deleteWithNotExistsId() {
    val authorityId = ObjectId()
    Mockito.`when`(authorityRepository.existsById(authorityId)).thenReturn(false)
    assertThrows(NotFoundException::class.java) {
      authorityService.deleteAuthority(authorityId.toString())
    }
  }
}
