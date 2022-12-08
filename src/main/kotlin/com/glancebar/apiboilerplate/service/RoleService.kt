package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.entity.RoleEntity
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.vo.RoleVO
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
interface RoleService {
  fun addRole(roleVO: RoleVO): ResponseEntity<OkResult>

  fun getRole(roleId: String): ResponseEntity<RoleEntity>

  fun getRoles(pageable: Pageable): ResponseEntity<Collection<RoleEntity>>

  fun updateRole(roleVO: RoleVO): ResponseEntity<OkResult>

  fun deleteRole(roleId: String): ResponseEntity<Any?>
}
