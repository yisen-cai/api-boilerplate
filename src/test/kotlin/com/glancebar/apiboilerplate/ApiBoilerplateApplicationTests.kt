package com.glancebar.apiboilerplate

import com.glancebar.wechat.WechatMiniProgramApi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiBoilerplateApplicationTests {

  @Autowired
  lateinit var wechatMiniProgramApi: WechatMiniProgramApi

  @Test
  @Disabled("Enable when there have APP_ID and APP_SECRET environment variable.")
  fun code2Session() {
    val code2SessionResult = wechatMiniProgramApi.code2Session("031AXC0w3XU3LV2rqy0w3icRl42AXC0C")
    println(code2SessionResult)
    Assertions.assertNotNull(code2SessionResult)
  }
}
