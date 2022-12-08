package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.entity.RoleEntity
import com.glancebar.apiboilerplate.exceptions.NotFoundException
import com.glancebar.apiboilerplate.exceptions.ParamsException
import com.glancebar.apiboilerplate.repository.RoleRepository
import com.glancebar.apiboilerplate.utils.Log
import com.glancebar.apiboilerplate.vo.RoleVO
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
class RoleServiceImplTest {

  @Autowired
  lateinit var roleService: RoleService

  @MockBean
  lateinit var roleRepository: RoleRepository

  companion object : Log()

  final val roleNameWithoutPrefix = "TEST"
  final val roleName = "ROLE_$roleNameWithoutPrefix"
  final val roleDescription = "Role description"

  val roleEntity = RoleEntity(
    id = ObjectId(),
    name = roleName,
    description = roleDescription
  )

  @Test
  internal fun addRole() {
    val roleVO = RoleVO()
    roleVO.name = roleName
    roleVO.description = roleDescription

    val before = RoleEntity(
      name = roleEntity.name,
      description = roleEntity.description
    )

    Mockito.`when`(roleRepository.existsByNameEquals(roleName)).thenReturn(false)
    Mockito.`when`(roleRepository.save(before)).thenReturn(roleEntity)

    val result = roleService.addRole(roleVO)

    assertEquals(HttpStatus.CREATED, result.statusCode)
  }

  @Test
  internal fun addRoleWithoutPrefix() {
    val roleVO = RoleVO()
    roleVO.name = roleNameWithoutPrefix
    roleVO.description = roleDescription

    val before = RoleEntity(
      name = roleEntity.name,
      description = roleEntity.description
    )

    Mockito.`when`(roleRepository.existsByNameEquals(roleName)).thenReturn(false)
    // pass roleVo with name equals HELLO, then save entity with name equals ROLE_HELLO
    Mockito.`when`(roleRepository.save(before)).thenReturn(roleEntity)

    val result = roleService.addRole(roleVO)

    assertEquals(HttpStatus.CREATED, result.statusCode)
  }

  @Test
  internal fun addRoleExists() {
    val roleVO = RoleVO()
    roleVO.name = roleName
    roleVO.description = roleDescription

    Mockito.`when`(roleRepository.existsByNameEquals(roleName)).thenReturn(true)

    assertThrows(ParamsException::class.java) {
      roleService.addRole(roleVO)
    }
  }

  @Test
  internal fun getRole() {
    Mockito.`when`(roleRepository.findTopByIdEquals(roleEntity.id!!)).thenReturn(roleEntity)

    val result = roleService.getRole(roleId = roleEntity.id!!.toString())

    assertEquals(roleEntity, result.body)
  }

  @Test
  internal fun getRoleWithInvalidId() {
    val roleId = "123"
    assertThrows(ParamsException::class.java) {
      roleService.getRole(roleId)
    }
  }

  @Test
  internal fun getNotExistsRole() {
    val roleId = ObjectId()
    Mockito.`when`(roleRepository.findTopByIdEquals(roleId)).thenReturn(null)
    assertThrows(NotFoundException::class.java) {
      roleService.getRole(roleId.toString())
    }
  }

  @Test
  internal fun getRoles() {
    val page = PageRequest.of(0, 10)
    Mockito.`when`(roleRepository.findAll(page)).thenReturn(PageImpl(listOf(roleEntity)))
    val result = roleService.getRoles(PageRequest.of(0, 10))
    assertTrue(result.body!!.isNotEmpty())
  }

  @Test
  internal fun updateRole() {
    val roleVO = RoleVO()
    roleVO.id = roleEntity.id.toString()
    roleVO.name = "NEW_NAME"
    roleVO.description = roleDescription

    val result = RoleEntity(
      id = roleEntity.id,
      name = roleVO.name,
      description = roleEntity.description
    )
    Mockito.`when`(roleRepository.existsById(roleEntity.id!!)).thenReturn(true)
    Mockito.`when`(roleRepository.save(roleEntity)).thenReturn(result)

    val updated = roleService.updateRole(roleVO)
    assertEquals(HttpStatus.RESET_CONTENT, updated.statusCode)
  }

  @Test
  internal fun updateNotExistsRole() {
    val roleVO = RoleVO()
    roleVO.id = roleEntity.id.toString()
    roleVO.name = "NEW_NAME"
    roleVO.description = roleDescription

    Mockito.`when`(roleRepository.existsById(roleEntity.id!!)).thenReturn(false)

    assertThrows(NotFoundException::class.java) {
      roleService.updateRole(roleVO)
    }
  }

  @Test
  internal fun updateRoleWithNameExists() {
    val roleVO = RoleVO()
    roleVO.id = roleEntity.id.toString()
    roleVO.name = "NEW_NAME"
    roleVO.description = roleDescription
    Mockito.`when`(roleRepository.existsById(roleEntity.id!!)).thenReturn(true)
    Mockito.`when`(roleRepository.existsByNameEquals(roleVO.name)).thenReturn(true)

    assertThrows(ParamsException::class.java) {
      roleService.updateRole(roleVO)
    }
  }

  @Test
  internal fun deleteRole() {
    Mockito.`when`(roleRepository.existsById(roleEntity.id!!)).thenReturn(true)

    // need special treats with functions return void
    // https://www.baeldung.com/mockito-void-methods
    val myRepository = mock(RoleRepository::class.java)
    Mockito.doNothing().`when`(roleRepository).deleteById(roleEntity.id!!)

    Mockito.doNothing().`when`(myRepository).deleteById(roleEntity.id!!)
    val result = roleService.deleteRole(roleEntity.id!!.toString())
    assertEquals(HttpStatus.NO_CONTENT, result.statusCode)
  }

  @Test
  internal fun deleteRoleWithInvalidId() {
    val roleId = "123"
    assertThrows(ParamsException::class.java) {
      roleService.deleteRole(roleId)
    }
  }

  @Test
  internal fun deleteNotExistsRole() {
    Mockito.`when`(roleRepository.existsById(roleEntity.id!!)).thenReturn(false)

    val myRepository = mock(RoleRepository::class.java)
    Mockito.doNothing().`when`(myRepository).deleteById(roleEntity.id!!)

    assertThrows(NotFoundException::class.java) {
      roleService.deleteRole(roleEntity.id!!.toString())
    }
  }
}
