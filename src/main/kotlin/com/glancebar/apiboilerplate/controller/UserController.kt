package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.dto.UserDTO
import com.glancebar.apiboilerplate.service.UserService
import com.glancebar.apiboilerplate.vo.RegisterVO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
@RestController
@RequestMapping("/users")
class UserController(val userService: UserService) {

    @PostMapping("/register")
    fun registerUser(@RequestBody registerVO: RegisterVO): UserDTO {
        return userService.registerUser(registerVO)
    }
}