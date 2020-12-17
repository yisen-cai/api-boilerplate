package com.glancebar.apiboilerplate.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 *
 * @author Ethan Gary
 * @date 2020/12/16
 */
@RestController
@RequestMapping("/authorities")
class AuthorityController(

) {

    @PostMapping
    fun addAuthorities() {

    }


    @GetMapping
    fun getAuthorities() {

    }

}