package com.glancebar.apiboilerplate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

/**
 * Password Encoder Bean initialization.
 */
@Configuration
class PasswordEncoderConfig(val sysProperties: SysProperties) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return Pbkdf2PasswordEncoder(sysProperties.secret)
    }
}