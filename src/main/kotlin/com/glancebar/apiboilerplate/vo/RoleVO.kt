package com.glancebar.apiboilerplate.vo

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import jakarta.validation.constraints.NotEmpty

/**
 * Add Role vo
 * @author YISEN
 * @date 2020/12/16
 */
class RoleVO {
  var id: String? = null

  @NotEmpty(message = "role name must set")
  var name: String = ""

  var description: String = ""

  var authorities: MutableSet<AuthorityEntity> = mutableSetOf()
}

// Below will not work with Validator
// class RoleVO(
//    @NotEmpty(message = "role name must set")
//    val name: String,
//    val description: String,
//    val authorities: MutableSet<AuthorityEntity> = mutableSetOf()
// )
