package com.glancebar.apiboilerplate

import com.glancebar.apiboilerplate.config.SysProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching


@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties(value = [SysProperties::class])
class ApiBoilerplateApplication

fun main(args: Array<String>) {
    runApplication<ApiBoilerplateApplication>(*args)
}
