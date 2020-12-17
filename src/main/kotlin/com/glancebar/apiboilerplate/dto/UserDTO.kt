package com.glancebar.apiboilerplate.dto

import com.glancebar.apiboilerplate.entity.GenderEnum
import java.time.LocalDate
import java.time.LocalDateTime


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
data class UserDTO(
    val id: String,
    val username: String,
    val gender: GenderEnum,
    val birthday: LocalDate,
    val createTime: LocalDateTime,
    val isDelete: Boolean,
    val isActive: Boolean
) {
}