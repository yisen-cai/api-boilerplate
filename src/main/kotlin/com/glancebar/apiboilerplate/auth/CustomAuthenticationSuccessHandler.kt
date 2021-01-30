package com.glancebar.apiboilerplate.auth

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 *
 * @author Ethan Gary
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