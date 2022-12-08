package com.glancebar.apiboilerplate.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
/**
 * This is a filter, main task is to extract auth info from request, and than store info to SecurityContext,
 * leaving AuthenticationProvider to authenticate request
 * @author YISEN
 * @date 2021/1/25
 */
class JwtAuthenticationFilter : OncePerRequestFilter() {

  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    filterChain: FilterChain
  ) {
    val header: String? = request.getHeader("Authorization")

    if (header != null && header.startsWith("Bearer ")) {
      // support this kind of authentication
      val authToken = header.substring(7)
      val authRequest = JwtAuthenticationToken(authToken)
      SecurityContextHolder.getContext().authentication = authRequest
    }

    filterChain.doFilter(request, response)
  }
}
