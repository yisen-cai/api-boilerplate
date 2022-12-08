package com.glancebar.apiboilerplate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

/**
 * Password Encoder Bean initialization.
 */
@Configuration
class PasswordEncoderConfig(val sysProperties: SysProperties) {

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8()
  }

  @Bean
  fun delegatingPasswordEncoder(): PasswordEncoder {
    val encoders = mutableMapOf<String, PasswordEncoder>()
    encoders["noop"] = NoOpPasswordEncoder.getInstance()
    encoders["bcrypt"] = BCryptPasswordEncoder()
    encoders["scrypt"] = SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8()
    encoders["pbkdf2"] = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8()
    return DelegatingPasswordEncoder("pbkdf2", encoders)
  }
}
