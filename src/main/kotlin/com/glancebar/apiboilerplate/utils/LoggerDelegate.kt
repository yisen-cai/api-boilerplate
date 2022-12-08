package com.glancebar.apiboilerplate.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
class LoggerDelegate : ReadOnlyProperty<Any?, Logger> {

  companion object {
    private fun <T> createLogger(clazz: Class<T>): Logger {
      return LoggerFactory.getLogger(clazz)
    }
  }

  private var logger: Logger? = null

  override fun getValue(thisRef: Any?, property: KProperty<*>): Logger {
    // thread safe
    synchronized(this) {
      if (logger == null) {
        logger = createLogger(thisRef!!.javaClass)
      }
    }
    return logger!!
  }
}
