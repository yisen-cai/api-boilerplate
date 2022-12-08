package com.glancebar.apiboilerplate.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

/**
 *
 * @author YISEN
 * @date 2021/1/28
 */
@Component
class CustomAuthenticationSuccessHandler :
  AuthenticationSuccessHandler {

  override fun onAuthenticationSuccess(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    authentication: Authentication?
  ) {
    // We do not need to do anything extra on REST authentication success, because there is no page to redirect to
    TODO()
  }
}
