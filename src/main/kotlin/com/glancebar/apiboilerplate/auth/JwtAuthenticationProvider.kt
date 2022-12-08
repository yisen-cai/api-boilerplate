package com.glancebar.apiboilerplate.auth

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

/**
 * Authenticate request by the token former filter extracted
 * @author YISEN
 * @date 2021/1/25
 */
@Component
class JwtAuthenticationProvider(
  val jwtUtil: JwtUtil,
  @Qualifier("userDetailServiceImpl")
  val userDetailsService: UserDetailsService
) : AuthenticationProvider {

  /**
   * Verify this kind of Authentication if supported
   */
  override fun supports(authentication: Class<*>?): Boolean {
//        return JwtAuthenticationToken::class.java.isAssignableFrom(authentication!!)
    return authentication == JwtAuthenticationToken::class.java
  }

//    override fun additionalAuthenticationChecks(
//        userDetails: UserDetails?,
//        authentication: UsernamePasswordAuthenticationToken?
//    ) {
//
//    }
//
//    override fun retrieveUser(username: String?, authentication: UsernamePasswordAuthenticationToken?): UserDetails {
//        val jwtAuthenticationToken = authentication as JwtAuthenticationToken
//        val jwtUsername = jwtUtil.parseToken(jwtAuthenticationToken.token) ?: throw JwtTokenMalFormedException("")
//        return userDetailsService.loadUserByUsername(jwtUsername)
//    }

  override fun authenticate(authentication: Authentication?): Authentication {
    val jwtAuthenticationToken = authentication as JwtAuthenticationToken
    val username =
      jwtUtil.parseToken(jwtAuthenticationToken.credentials as String) ?: throw JwtTokenMalFormedException("")
    val userDetails = userDetailsService.loadUserByUsername(username)
    return createSuccessAuthentication(
      authentication,
      userDetails
    )
  }

  /**
   * {@link AbstractUserDetailsAuthenticationProvider}
   */
  fun createSuccessAuthentication(
    authentication: Authentication?,
    user: UserDetails?
  ): Authentication {
    val result = JwtAuthenticationToken(
      user,
      authentication!!.credentials,
      user!!.authorities
    )
    // set the authentication is success
    result.isAuthenticated = true
    // set request details
    result.details = ""
    return result
  }
}
