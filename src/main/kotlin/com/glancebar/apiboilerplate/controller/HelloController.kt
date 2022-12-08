package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.utils.Log
import com.glancebar.apiboilerplate.utils.LoggerDelegate
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.wechat.WechatMiniProgramApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// @CrossOrigin(origins = ["http://domain2.com"], maxAge = 3600)
@RestController
@RequestMapping("/hello")
class HelloController(
  val wechatMiniProgramApi: WechatMiniProgramApi
) {

  private val logger1: Logger = LoggerFactory.getLogger(this.javaClass)
  private val logger2 by LoggerDelegate()

  companion object : Log()

  @GetMapping
  fun hello(): ResponseEntity<Any> {
    return ResponseEntity.ok(OkResult())
  }

  // Also apply to method, will then combine both annotation to create merged configuration
//    @CrossOrigin(origins = ["http://domain2.com"])
  @GetMapping("/wechat")
  fun wechat(@AuthenticationPrincipal user: User, @RequestParam("jsCode") jsCode: String): ResponseEntity<Any> {
    logger.info("OK")
    // retrieve authentication
//        val authentication = SecurityContextHolder.getContext().authentication
    val code2SessionResult = wechatMiniProgramApi.code2Session(jsCode)
    return ResponseEntity.ok(code2SessionResult)
  }
}
