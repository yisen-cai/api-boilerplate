package com.glancebar.apiboilerplate.vo

import javax.validation.constraints.NotBlank

data class WechatLoginVO(
    @NotBlank(message = "Wechat login jsCode can't be null")
    val jsCode: String
)
