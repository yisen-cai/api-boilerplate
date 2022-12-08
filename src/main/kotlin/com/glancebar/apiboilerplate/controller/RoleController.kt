package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.entity.RoleEntity
import com.glancebar.apiboilerplate.service.RoleService
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.vo.RoleVO
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
@RestController
@RequestMapping("/roles")
class RoleController(
  val roleService: RoleService,
) {

  /**
   * get role
   */
  @PostMapping
  fun addRole(@Valid @RequestBody roleVO: RoleVO): ResponseEntity<OkResult> {
    return roleService.addRole(roleVO)
  }

  @GetMapping("/{roleId}")
  fun getRole(@PathVariable roleId: String): ResponseEntity<RoleEntity> {
    return roleService.getRole(roleId)
  }

  /**
   * Retrieve all roles
   */
  @GetMapping
  fun getRoles(@PageableDefault pageable: Pageable): ResponseEntity<Collection<RoleEntity>> {
    return roleService.getRoles(pageable)
  }

  /**
   * Grant or Retrieve authorities
   */
  @PutMapping("/{roleId}")
  fun update(@PathVariable roleId: String, @Valid @RequestBody roleVO: RoleVO): ResponseEntity<OkResult> {
    roleVO.id = roleId
    return roleService.updateRole(roleVO)
  }

  /**
   * Delete role by given roleId
   */
  @DeleteMapping("/{roleId}")
  fun deleteRole(@PathVariable roleId: String): ResponseEntity<Any?> {
    return roleService.deleteRole(roleId)
  }
}
