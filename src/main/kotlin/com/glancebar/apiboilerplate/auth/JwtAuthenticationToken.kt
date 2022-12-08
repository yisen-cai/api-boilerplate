package com.glancebar.apiboilerplate.auth

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 * A Authentication class, to store JWT token and used by Authentication Provider support method
 *
 * @author YISEN
 * @date 2021/1/28
 */
class JwtAuthenticationToken(
  private var credentials: Any? = null,
  authorities: Collection<GrantedAuthority?> = emptyList()
) : AbstractAuthenticationToken(authorities) {

  private var principal: Any? = null

  constructor(
    principal: Any?,
    credentials: Any?,
    authorities: Collection<GrantedAuthority?>
  ) : this(credentials, authorities) {
    this.principal = principal
  }

  /**
   * The credentials that prove the principal is correct, here it stores jwt token
   */
  override fun getCredentials(): Any? {
    return this.credentials
  }

  /**
   * The identity of the principal being authenticated. Like authenticated user
   */
  override fun getPrincipal(): Any? {
    return this.principal
  }
}
