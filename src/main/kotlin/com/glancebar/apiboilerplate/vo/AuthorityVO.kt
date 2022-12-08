package com.glancebar.apiboilerplate.vo

import jakarta.validation.constraints.NotBlank

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
class AuthorityVO {

  var id: String? = null

  @NotBlank(message = "name must be set")
  var name: String = ""

  var description: String = ""
}
