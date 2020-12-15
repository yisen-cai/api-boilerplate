package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.dto.UserDTO
import com.glancebar.apiboilerplate.exceptions.ParamsException
import com.glancebar.apiboilerplate.service.UserService
import com.glancebar.apiboilerplate.vo.RegisterVO
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
@RestController
@RequestMapping("/auth")
class AuthController(val userService: UserService) {

    @PostMapping("/register")
    fun registerUser(@RequestBody @Valid registerVO: RegisterVO, result: BindingResult): UserDTO {
        if (result.hasErrors()) {
            throw ParamsException(result = result)
        }
        return userService.registerUser(registerVO)
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