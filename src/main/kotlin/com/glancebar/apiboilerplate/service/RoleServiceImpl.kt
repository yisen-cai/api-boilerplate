package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.config.SysProperties
import com.glancebar.apiboilerplate.entity.RoleEntity
import com.glancebar.apiboilerplate.exceptions.NotFoundException
import com.glancebar.apiboilerplate.exceptions.ParamsException
import com.glancebar.apiboilerplate.repository.AuthorityRepository
import com.glancebar.apiboilerplate.repository.RoleRepository
import com.glancebar.apiboilerplate.utils.*
import com.glancebar.apiboilerplate.vo.RoleVO
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.net.URI

/**
 *
 * @author YISEN
 * @date 2020/12/19
 */
@Service
class RoleServiceImpl(
  val roleRepository: RoleRepository,
  val authorityRepository: AuthorityRepository,
  val sysProperties: SysProperties,
) : RoleService {

  companion object : Log()

  /**
   * verify role whether exists then save role
   */
  override fun addRole(roleVO: RoleVO): ResponseEntity<OkResult> {
    validateRoleVO(roleVO)

    val roleEntity = getEntity(roleVO)

    // then save role
    val result = roleRepository.save(roleEntity)
    val resultURI = URI("/roles/${result.id}")

    logger.info("Role $result added")

    return ResponseEntity.created(resultURI)
      .body(OkResult("Role: `${result.name}` created", resultURI))
  }

  override fun getRole(roleId: String): ResponseEntity<RoleEntity> {
    val objectId = validateObjectId(roleId)
    val result = roleRepository.findTopByIdEquals(objectId)
      ?: throw NotFoundException(ErrResult("Role id=$roleId is not exists", 1))

    return ResponseEntity.ok(result)
  }

  override fun getRoles(pageable: Pageable): ResponseEntity<Collection<RoleEntity>> {
    val results = roleRepository.findAll(pageable).toList()
    return ResponseEntity.ok(results)
  }

  override fun updateRole(roleVO: RoleVO): ResponseEntity<OkResult> {
    verifyRoleExists(roleVO.id)
    validateRoleVO(roleVO)
    val roleEntity = getEntity(roleVO)
    roleRepository.save(roleEntity)
    return ResponseEntity.status(HttpStatus.RESET_CONTENT).build()
  }

  override fun deleteRole(roleId: String): ResponseEntity<Any?> {
    val objectId = verifyRoleExists(roleId)
    roleRepository.deleteById(objectId)
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
  }

  private fun verifyRoleExists(roleId: String?): ObjectId {
    return verifyEntityExists(roleId, roleRepository as CrudRepository<RoleEntity, ObjectId>, logger)
  }

  /**
   * verify roleName does not exist
   */
  private fun validateRoleVO(roleVO: RoleVO) {
    if (roleRepository.existsByNameEquals(roleVO.name)) {
      val msg = "The name ${roleVO.name} of role already exists!"
      logger.debug(msg)
      throw ParamsException(errResult = ErrResult(msg))
    }
    addPrefix(roleVO)
  }

  private fun addPrefix(roleVO: RoleVO) {
    if (!roleVO.name.startsWith(sysProperties.rolePrefix)) {
      roleVO.name = "${sysProperties.rolePrefix}$roleVO"
    }
  }

  private fun getEntity(roleVO: RoleVO): RoleEntity {
    val roleEntity = RoleEntity(
      name = roleVO.name,
      description = roleVO.description,
      authorities = roleVO.authorities
    )
    // when updated
    if (roleVO.id != null) {
      roleEntity.id = validateObjectId(roleVO.id)
    }
    return roleEntity
  }
}
