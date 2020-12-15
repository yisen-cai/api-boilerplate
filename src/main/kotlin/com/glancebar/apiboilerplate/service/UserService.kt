package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.dto.UserDTO
import com.glancebar.apiboilerplate.vo.RegisterVO


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
interface UserService {
    fun registerUser(user: RegisterVO): UserDTO
}