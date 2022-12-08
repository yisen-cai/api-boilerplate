package com.glancebar.apiboilerplate.utils

import com.glancebar.apiboilerplate.dto.UserDTO

/**
 *
 * @author YISEN
 * @date 2021/2/2
 */
data class WechatAuthResult(
  val user: UserDTO,
  val auth: AuthResult
)
