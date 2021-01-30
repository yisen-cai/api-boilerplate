package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.utils.AuthResult
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.vo.UserVO
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
interface UserService {
    fun registerUser(userVO: UserVO): ResponseEntity<OkResult>

    fun loginUser(user: User): ResponseEntity<AuthResult>
}