package com.glancebar.apiboilerplate.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

/**
 *
 * @author YISEN
 * @date 2021/1/28
 */
@Component
class RestAuthenticationEntryPoint : AuthenticationEntryPoint {
  override fun commence(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    authException: AuthenticationException?
  ) {
    // This is invoked when user tries to access a secured REST resource without supplying any credentials
    // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
    response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
  }
}
