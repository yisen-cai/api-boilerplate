package com.glancebar.apiboilerplate.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component

/**
 *
 * @author YISEN
 * @date 2021/1/29
 */
@Component
class CustomAuthenticationFailureHandler : AuthenticationFailureHandler {

  override fun onAuthenticationFailure(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    exception: AuthenticationException?
  ) {
    TODO("Not yet implemented")
  }
}
