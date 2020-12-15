package com.glancebar.apiboilerplate.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * UserEntity
 * @author Ethan Gary
 * @date 2020//01
 */
@Document(value = "user")
class UserEntity(
    @Id
    var id: ObjectId = ObjectId(),
    var username: String,
    var password: String,
    var birthday: LocalDate,
    var gender: GenderEnum = GenderEnum.UNKNOWN,
    var roles: MutableSet<RoleEntity> = mutableSetOf(),
    var authorities: MutableSet<AuthorityEntity> = mutableSetOf(),
    var createTime: LocalDateTime = LocalDateTime.now(),
    var isDelete: Boolean = false,
    var isActive: Boolean = true
) {

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
}