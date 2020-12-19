package com.glancebar.apiboilerplate.vo

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import com.glancebar.apiboilerplate.entity.RoleEntity
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


/**
 * User register vo
 * @author Ethan Gary
 * @date 2020/12/15
 */
class UserVO {

    var id: String? = null

    @NotBlank(message = "username name can't be blank")
    var username: String = ""

    @NotBlank(message = "password can't be blank")
    var password: String = ""

    @NotNull(message = "birthday must be set")
    var birthday: Long = 0

    var gender: String = "unknown"

    var roles: MutableSet<RoleEntity> = mutableSetOf()

    var authorities: MutableSet<AuthorityEntity> = mutableSetOf()
}