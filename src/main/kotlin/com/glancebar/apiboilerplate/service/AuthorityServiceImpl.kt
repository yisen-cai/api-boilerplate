package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import com.glancebar.apiboilerplate.exceptions.NotFoundException
import com.glancebar.apiboilerplate.exceptions.ParamsException
import com.glancebar.apiboilerplate.repository.AuthorityRepository
import com.glancebar.apiboilerplate.utils.ErrResult
import com.glancebar.apiboilerplate.utils.Log
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.utils.validateObjectId
import com.glancebar.apiboilerplate.vo.AuthorityVO
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.net.URI

/**
 *
 * @author YISEN
 * @date 2020/12/18
 */
@Service
class AuthorityServiceImpl(
  val authorityRepository: AuthorityRepository,
) : AuthorityService {

  companion object : Log()

  override fun addAuthority(authorityVO: AuthorityVO): ResponseEntity<OkResult> {
    validateAuthorityVO(authorityVO)
    val authorityEntity = getEntity(authorityVO)

    val result = authorityRepository.save(authorityEntity)

    val location = URI("/authorities/${result.id}")
    return ResponseEntity
      .created(location)
      .body(OkResult(msg = "Authority `${result.name}` created", location = location))
  }

  override fun getAuthority(authorityId: String): ResponseEntity<AuthorityEntity> {
    val objectId = validateObjectId(authorityId)
    val result = authorityRepository.findTopByIdEquals(objectId)
      ?: throw NotFoundException(ErrResult("Authority id=`$authorityId` does not exist"))
    return ResponseEntity.ok(result)
  }

  override fun getAuthorities(pageable: Pageable): ResponseEntity<Collection<AuthorityEntity>> {
    val results = authorityRepository.findAll(pageable).toList()
    return ResponseEntity.ok(results)
  }

  override fun updateAuthority(authorityVO: AuthorityVO): ResponseEntity<Any?> {
    verifyAuthorityExists(authorityVO)
    validateAuthorityVO(authorityVO)
    val authorityEntity = getEntity(authorityVO)
    authorityRepository.save(authorityEntity)
    return ResponseEntity.status(HttpStatus.RESET_CONTENT).build()
  }

  override fun deleteAuthority(authorityId: String): ResponseEntity<Any?> {
    val objectId = validateObjectId(authorityId)
    if (!authorityRepository.existsById(objectId)) {
      val msg = "Authority id=`$authorityId` does not exist"
      logger.debug(msg)
      throw NotFoundException(ErrResult(msg))
    }
    authorityRepository.deleteById(objectId)
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
  }

  private fun verifyAuthorityExists(authorityVO: AuthorityVO) {
    val objectId = validateObjectId(authorityVO.id)
    if (!authorityRepository.existsById(objectId)) {
      logger.debug("Authority id=`${authorityVO.id}` does not exist")
      throw NotFoundException(ErrResult("Authority id=`${authorityVO.id}` does not exist"))
    }
  }

  private fun validateAuthorityVO(authorityVO: AuthorityVO) {
    if (authorityRepository.existsByNameEquals(authorityVO.name)) {
      val msg = "Authority name=`${authorityVO.name}` already exists"
      logger.debug(msg)
      throw ParamsException(ErrResult(msg))
    }
  }

  private fun getEntity(authorityVO: AuthorityVO): AuthorityEntity {
    val result = AuthorityEntity(
      name = authorityVO.name,
      description = authorityVO.description
    )
    // when updated
    if (authorityVO.id != null) {
      result.id = validateObjectId(authorityVO.id)
    }
    return result
  }
}
