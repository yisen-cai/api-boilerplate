package com.glancebar.apiboilerplate.aspect

import com.glancebar.apiboilerplate.utils.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

/**
 * created by YISEN
 * 2019-07-09
 */
@Aspect
@Component
class RequestAspect {

  companion object : Log()

  @Around("execution(* com.glancebar.controller.*.*(..))")
  @Throws(Throwable::class)
  fun handlerControllerMethod(pjp: ProceedingJoinPoint): Any {

    logger.info("request aspect init......")

    val method = pjp.proceed()

    val args = pjp.args
    for (arg in args) {
      println("args is ： $arg")
    }
    return method
  }
}
