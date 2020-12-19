package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.service.UserService
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.vo.UserVO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


/**
 * Authentication and Authorization related
 * @author Ethan Gary
 * @date 2020/12/15
 */
@RestController
@RequestMapping("/auth")
class AuthController(
    val userService: UserService
) {

    /**
     * Created success return OkResult with status created
     */
    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody userVO: UserVO): ResponseEntity<OkResult> {
        return userService.registerUser(userVO)
    }



    @PutMapping("/logout")
    fun logout() {
        TODO("Logout")
    }

    @PutMapping("/login")
    fun login() {
        TODO("Login")
    }
}