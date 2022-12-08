package com.glancebar.apiboilerplate.dto

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import com.glancebar.apiboilerplate.entity.GenderEnum
import com.glancebar.apiboilerplate.entity.RoleEntity
import java.time.LocalDate
import java.time.LocalDateTime

/**
 *
 * @author YISEN
 * @date 2020/12/15
 */
data class UserDTO(
  val id: String,
  val username: String,
  val gender: GenderEnum,
  val birthday: LocalDate,
  val roles: Collection<RoleEntity>,
  val authorities: Collection<AuthorityEntity>,
  val createTime: LocalDateTime,
  val isDelete: Boolean,
  val isActive: Boolean,
)
