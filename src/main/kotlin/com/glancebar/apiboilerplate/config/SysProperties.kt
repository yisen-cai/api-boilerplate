package com.glancebar.apiboilerplate.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "system")
data class SysProperties(
  val secret: String,
  val rolePrefix: String,
  val expiration: Long
)
