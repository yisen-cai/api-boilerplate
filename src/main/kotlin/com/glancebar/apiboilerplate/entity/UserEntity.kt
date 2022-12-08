package com.glancebar.apiboilerplate.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * UserEntity
 * @author YISEN
 * @date 2020//01
 */
@Document(value = "user")
data class UserEntity(
  @Id
  var id: ObjectId? = null,
  var username: String,
  var password: String,
  var birthday: LocalDate = LocalDate.now(),
  var gender: GenderEnum = GenderEnum.UNKNOWN,
  var wechatOpenId: String = "",

  @DBRef
  var roles: MutableSet<RoleEntity> = mutableSetOf(),

  @DBRef
  var authorities: MutableSet<AuthorityEntity> = mutableSetOf(),

  var createTime: LocalDateTime = LocalDateTime.now(),
  var isDelete: Boolean = false,
  var isActive: Boolean = true,
) : Serializable {

  fun addRoles(role: RoleEntity) {
    roles.add(role)
  }

  fun removeRoles(role: RoleEntity) {
    roles.remove(role)
  }

  fun addAuthority(authority: AuthorityEntity) {
    authorities.add(authority)
  }

  fun removeAuthority(authority: AuthorityEntity) {
    authorities.remove(authority)
  }

  // must be implemented, will be used by mock test
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is UserEntity) return false

    if (id != other.id) return false

    return true
  }

  override fun hashCode(): Int {
    return id.hashCode()
  }
}
