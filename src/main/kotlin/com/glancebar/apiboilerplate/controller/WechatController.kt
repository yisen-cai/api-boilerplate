package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.service.UserService
import com.glancebar.apiboilerplate.utils.WechatAuthResult
import com.glancebar.apiboilerplate.vo.WechatLoginVO
import com.glancebar.apiboilerplate.vo.WechatRegisterVO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author YISEN
 * @date 2021/2/1
 */
@RestController
@RequestMapping("/wechat")
class WechatController(
  val userService: UserService
) {

  /**
   * Wechat MiniProgram login
   */
  @PostMapping("/mini-program/login")
  fun miniProgramLogin(@RequestBody wechatLoginVO: WechatLoginVO): ResponseEntity<WechatAuthResult> {
    return userService.wechatLogin(wechatLoginVO)
  }

  @PostMapping("/mini-program/register")
  fun miniProgramRegister(@RequestBody wechatRegisterVO: WechatRegisterVO) {
  }
}
