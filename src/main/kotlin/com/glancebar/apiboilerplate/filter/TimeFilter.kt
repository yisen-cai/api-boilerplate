package com.glancebar.apiboilerplate.filter

import com.glancebar.apiboilerplate.utils.Log
import jakarta.servlet.*

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
class TimeFilter : Filter {

  companion object : Log()

  override fun init(filterConfig: FilterConfig?) {
  }

  override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
    logger.info("time filter init....")
    val start = System.currentTimeMillis()
    chain!!.doFilter(request, response)
    val end = System.currentTimeMillis()
    logger.info("time filter ended....")
    logger.info("time filter last: " + (end - start) + " milliseconds")
  }

  override fun destroy() {
  }
}
