package com.glancebar.apiboilerplate.interceptor

import com.glancebar.apiboilerplate.utils.Log
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

/**
 *
 * @author YISEN
 * @date 2020/12/21
 */
@Component
class TimeInterceptor : HandlerInterceptor {
  companion object : Log()

  override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
    logger.info("pre handle")
    request.setAttribute("startTime", System.currentTimeMillis())
    try {
      logger.info((handler as HandlerMethod).method.name)
    } catch (e: ClassCastException) {
    }
    return true
  }

  override fun postHandle(
    request: HttpServletRequest,
    response: HttpServletResponse,
    handler: Any,
    modelAndView: ModelAndView?,
  ) {
    logger.info("post handle")
    val startTime = request.getAttribute("startTime") as Long
    logger.info("last" + (System.currentTimeMillis() - startTime) + " milliseconds")
  }

  override fun afterCompletion(
    request: HttpServletRequest,
    response: HttpServletResponse,
    handler: Any,
    ex: Exception?,
  ) {
    logger.info("after completion")
    ex?.printStackTrace()
  }
}
