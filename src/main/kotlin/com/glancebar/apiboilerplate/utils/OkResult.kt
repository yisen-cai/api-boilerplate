package com.glancebar.apiboilerplate.utils

import java.net.URI

/**
 *
 * @author YISEN
 * @date 2020/12/17
 */
data class OkResult(
  val msg: String = "OK",
  val location: URI? = null
)
