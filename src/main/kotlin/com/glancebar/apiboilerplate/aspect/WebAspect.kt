package com.glancebar.apiboilerplate.aspect

import com.glancebar.apiboilerplate.utils.Log
import org.aopalliance.intercept.Joinpoint
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
@Aspect
@Component
class WebAspect {

  companion object : Log()

  private fun before(joinpoint: Joinpoint) {
    val attributes: ServletRequestAttributes =
      RequestContextHolder.getRequestAttributes() as ServletRequestAttributes

    val request = attributes.request
    val response = attributes.response

    response!!.setHeader(
      "Access-Control-Allow-Headers",
      "Content-Type,Content-Length,Authorization,X-Requested-With"
    )
    response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS")

    val method = request.method

    if (method == "OPTIONS") {
      response.status = 200
    }

    logger.debug("url=${request.requestURL}")
    logger.debug("method=${request.method}")
//        logger.debug("method class=${}")
  }
}
