package com.glancebar.apiboilerplate.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class WebLogAspect {

  private val logger: Logger = LoggerFactory.getLogger(WebLogAspect::class.java)

  /**
   * 定义一个切入，只要是为io.intodream..web下public修饰的方法都要切入
   */
  @Pointcut(value = "execution(public * top.yisen614.controller.*.*(..))")
  fun webLog() {
  }

  /**
   * 切面的连接点，并声明在该连接点进入之前需要做的一些事情
   */
  @Before(value = "webLog()")
  @Throws(Throwable::class)
  fun doBefore(joinPoint: JoinPoint) {
    logger.info("aspect init....................")
  }
}
