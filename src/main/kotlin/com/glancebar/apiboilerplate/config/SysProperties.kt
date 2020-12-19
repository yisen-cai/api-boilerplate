package com.glancebar.apiboilerplate.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "system")
class SysProperties(
    val secret: String,
    val rolePrefix: String
) {
}