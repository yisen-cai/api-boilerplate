package com.glancebar.apiboilerplate.controller

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import com.glancebar.apiboilerplate.service.AuthorityService
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.vo.AuthorityVO
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
@RestController
@RequestMapping("/authorities")
class AuthorityController(
  val authorityService: AuthorityService,
) {

  @PostMapping
  fun addAuthorities(@Valid @RequestBody authorityVO: AuthorityVO): ResponseEntity<OkResult> {
    return authorityService.addAuthority(authorityVO)
  }

  @GetMapping("/{authorityId}")
  fun getAuthority(@PathVariable authorityId: String): ResponseEntity<AuthorityEntity> {
    return authorityService.getAuthority(authorityId)
  }

  @PutMapping("/{authorityId}")
  fun updateAuthority(
    @Valid @RequestBody authorityVO: AuthorityVO,
    @PathVariable authorityId: String,
  ): ResponseEntity<Any?> {

    authorityVO.id = authorityId
    return authorityService.updateAuthority(authorityVO)
  }

  @DeleteMapping("/{authorityId}")
  fun deleteAuthority(@PathVariable authorityId: String): ResponseEntity<Any?> {
    return authorityService.deleteAuthority(authorityId)
  }

  @GetMapping
  fun getAuthorities(@PageableDefault pageable: Pageable): ResponseEntity<Collection<AuthorityEntity>> {
    return authorityService.getAuthorities(pageable)
  }
}
