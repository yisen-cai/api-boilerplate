package com.glancebar.apiboilerplate.actuator

import org.springframework.boot.actuate.health.AbstractHealthIndicator
import org.springframework.boot.actuate.health.Health
import org.springframework.stereotype.Component
import java.io.IOException
import java.net.InetAddress
import java.net.UnknownHostException

/**
 * A health indicator
 * @author YISEN
 * @date 2020/12/22
 */
@Component
class WWWHealthIndicator : AbstractHealthIndicator() {

  override fun doHealthCheck(builder: Health.Builder?) {
    if (ping()) {
      builder?.withDetail("message", "当前服务可以访问万维网")?.up()
    } else {
      builder?.withDetail("message", "当前无法访问万维网")?.outOfService()
    }
  }

  fun ping(): Boolean {
    try {
      return InetAddress.getByName(BUILD_HOST).isReachable(TIME_OUT)
    } catch (e: UnknownHostException) {
      e.printStackTrace()
    } catch (e: IOException) {
      e.printStackTrace()
    }
    return false
  }

  companion object {
    private const val BUILD_HOST = "www.baidu.com"
    private const val TIME_OUT = 3000
  }
}
