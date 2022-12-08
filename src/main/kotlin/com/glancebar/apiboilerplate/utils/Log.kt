package com.glancebar.apiboilerplate.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
abstract class Log {
  var logger: Logger = LoggerFactory.getLogger(this.javaClass)
}
