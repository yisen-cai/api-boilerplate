package com.glancebar.apiboilerplate.vo

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


/**
 * Modify Role vo
 * @author Ethan Gary
 * @date 2020/12/16
 */
class ModifyRoleVO {
    @NotNull(message = "isGrant can't be null")
    var isGrant: Boolean = true

    @NotEmpty(message = "authorities can't be empty")
    var authorities: MutableSet<AuthorityEntity> = mutableSetOf()
}