package com.glancebar.apiboilerplate.exceptions

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
class MyAuthenticationFailureHandler : AuthenticationFailureHandler {

  override fun onAuthenticationFailure(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    exception: AuthenticationException?
  ) {
    TODO("Not yet implemented")
  }
}
