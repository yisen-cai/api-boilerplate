package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.utils.Log
import com.glancebar.apiboilerplate.utils.LoggerDelegate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {

    private val logger1: Logger = LoggerFactory.getLogger(this.javaClass)
    private val logger2 by LoggerDelegate()

    companion object : Log()

    @GetMapping
    fun hello(): ResponseEntity<Any> {
        logger.info("OK")
        return ResponseEntity.ok("OK")
    }
}