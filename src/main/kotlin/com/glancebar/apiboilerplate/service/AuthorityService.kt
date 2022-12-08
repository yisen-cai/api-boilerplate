package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.vo.AuthorityVO
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity

/**
 *
 * @author YISEN
 * @date 2020/12/18
 */
interface AuthorityService {

  fun addAuthority(authorityVO: AuthorityVO): ResponseEntity<OkResult>

  fun getAuthority(authorityId: String): ResponseEntity<AuthorityEntity>

  fun getAuthorities(pageable: Pageable): ResponseEntity<Collection<AuthorityEntity>>

  fun updateAuthority(authorityVO: AuthorityVO): ResponseEntity<Any?>

  fun deleteAuthority(authorityId: String): ResponseEntity<Any?>
}
