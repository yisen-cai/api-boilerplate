package com.glancebar.apiboilerplate.exceptions

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 *
 * @author Ethan Gary
 * @date 2020/12/16
 */
class MyAuthenticationFailureHandler :AuthenticationFailureHandler{

    override fun onAuthenticationFailure(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        exception: AuthenticationException?
    ) {
        TODO("Not yet implemented")
    }

}