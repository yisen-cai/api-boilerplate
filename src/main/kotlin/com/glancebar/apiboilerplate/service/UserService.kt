package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.vo.RegisterVO
import org.springframework.http.ResponseEntity


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
interface UserService {
    fun registerUser(user: RegisterVO): ResponseEntity<OkResult>
}