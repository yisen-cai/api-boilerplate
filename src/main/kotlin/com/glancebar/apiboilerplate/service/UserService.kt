package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.utils.AuthResult
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.utils.WechatAuthResult
import com.glancebar.apiboilerplate.vo.UserVO
import com.glancebar.apiboilerplate.vo.WechatLoginVO
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User

/**
 *
 * @author YISEN
 * @date 2020/12/15
 */
interface UserService {
  fun registerUser(userVO: UserVO): ResponseEntity<OkResult>

  fun loginUser(user: User): ResponseEntity<AuthResult>

  fun wechatLogin(wechatLoginVO: WechatLoginVO): ResponseEntity<WechatAuthResult>
}
