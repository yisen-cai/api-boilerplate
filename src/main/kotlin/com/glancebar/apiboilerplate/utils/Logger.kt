package com.glancebar.apiboilerplate.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Log inline function
 * @author YISEN
 * @date 2020/12/16
 */
inline fun <reified T> logger(): Logger {
  return LoggerFactory.getLogger(T::class.java)
}
