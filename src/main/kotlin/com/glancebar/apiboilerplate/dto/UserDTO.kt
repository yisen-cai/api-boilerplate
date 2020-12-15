package com.glancebar.apiboilerplate.dto

import com.glancebar.apiboilerplate.entity.GenderEnum


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
data class UserDTO(
    val id: String,
    val username: String,
    val gender: GenderEnum,
    val birthday: Long,
    val createTime: Long,
    val isDelete: Boolean,
    val isActive: Boolean
) {
}