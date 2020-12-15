package com.glancebar.apiboilerplate

import com.glancebar.apiboilerplate.config.SysSecretProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableConfigurationProperties(value = [SysSecretProperties::class])
class ApiBoilerplateApplication

fun main(args: Array<String>) {
    runApplication<ApiBoilerplateApplication>(*args)
}
