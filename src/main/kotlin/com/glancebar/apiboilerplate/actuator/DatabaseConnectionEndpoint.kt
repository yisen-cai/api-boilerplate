package com.glancebar.apiboilerplate.actuator

import org.springframework.boot.actuate.endpoint.annotation.Endpoint
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation
import org.springframework.stereotype.Component

/**
 * Actuator custom endpoint, used to check mongodb connection
 * @author YISEN
 * @date 2021/01/05
 */
@Component
@Endpoint(id = "db-check", enableByDefault = true)
class DatabaseConnectionEndpoint {

  @ReadOperation
  fun test(): Map<String, Any> {
    TODO("Check mongodb connection")
  }
}
