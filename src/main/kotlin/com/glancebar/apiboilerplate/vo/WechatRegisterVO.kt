package com.glancebar.apiboilerplate.vo

/**
 *
 * @author YISEN
 * @date 2021/2/2
 */
data class WechatRegisterVO(
  val avatarUrl: String,
  val city: String,
  val country: String,
  val gender: Int,
  val nickName: String,
  val province: String
)
