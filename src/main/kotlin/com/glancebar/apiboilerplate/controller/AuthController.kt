package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.service.UserService
import com.glancebar.apiboilerplate.utils.AuthResult
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.vo.UserVO
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.*

/**
 * Authentication and Authorization related
 * @author YISEN
 * @date 2020/12/15
 */
@RestController
@RequestMapping("/auth")
class AuthController(
  val userService: UserService,
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

  @PostMapping("/login")
  fun login(@AuthenticationPrincipal user: User): ResponseEntity<AuthResult> {
    val authentication = SecurityContextHolder.getContext().authentication
    return userService.loginUser(user)
  }
}
