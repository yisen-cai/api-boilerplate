package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.auth.JwtUtil
import com.glancebar.apiboilerplate.config.SysProperties
import com.glancebar.apiboilerplate.entity.GenderEnum
import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.exceptions.ParamsException
import com.glancebar.apiboilerplate.exceptions.WechatNonExistsException
import com.glancebar.apiboilerplate.repository.UserRepository
import com.glancebar.apiboilerplate.utils.*
import com.glancebar.apiboilerplate.vo.UserVO
import com.glancebar.apiboilerplate.vo.WechatLoginVO
import com.glancebar.wechat.WechatMiniProgramApi
import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.net.URI
import java.time.Instant
import java.time.ZoneId

/**
 *
 * @author YISEN
 * @date 2020/12/15
 */
@Service
class UserServiceImpl(
  val userRepository: UserRepository,
  val passwordEncoder: PasswordEncoder,
  val jwtUtil: JwtUtil,
  val sysProperties: SysProperties,
  val wechatMiniProgramApi: WechatMiniProgramApi
) : UserService {

  companion object : Log()

  /**
   * Using given param to register a user
   */
  override fun registerUser(userVO: UserVO): ResponseEntity<OkResult> {
    validateUserVO(userVO)
    val userEntity = getEntity(userVO)
    val result = userRepository.save(userEntity)
    logger.info("User $result registered")
    val resultURI = URI("/users/${result.id}")

    // when entity was created, should return created status, and should not return relative info, return and id better
    return ResponseEntity
      .created(resultURI)
      .body(OkResult(msg = "User: `${result.username}` created", location = resultURI))
  }

  override fun loginUser(user: User): ResponseEntity<AuthResult> {
    val result = generateAuthResult(user)
    logger.debug("User ${user.username} is logged in")
    return ResponseEntity
      .ok(result)
  }

  override fun wechatLogin(wechatLoginVO: WechatLoginVO): ResponseEntity<WechatAuthResult> {
    val code2SessionResult = wechatMiniProgramApi.code2Session(wechatLoginVO.jsCode)
    val user = userRepository.findTopByWechatOpenIdEquals(code2SessionResult.openid)
      ?: throw WechatNonExistsException(
        ErrResult(
          "User does not register!"
        )
      )

    // generate jwt token for login user
    val expiration = System.currentTimeMillis() + sysProperties.expiration
    return ResponseEntity.ok(
      WechatAuthResult(
        user,
        AuthResult(
          jwtUtil.generateToken(user, expiration),
          expiration
        )
      )
    )
  }

  /**
   * TODO
   *
   * @param userId
   * @return
   */
  fun verifyUserExists(userId: String?): ObjectId {
    return verifyEntityExists(userId, userRepository as CrudRepository<UserEntity, ObjectId>, logger)
  }

  /**
   * TODO
   *
   * @param userVO
   */
  fun validateUserVO(userVO: UserVO) {
    if (userRepository.existsByUsernameEquals(userVO.username)) {
      val msg = "User username=`${userVO.username}` already exists"
      logger.debug(msg)
      throw ParamsException(ErrResult(msg))
    }
  }

  private fun generateAuthResult(user: User): AuthResult {
    val expiration = System.currentTimeMillis() + sysProperties.expiration
    val token = jwtUtil.generateToken(user, expiration)
    return AuthResult(token, expiration)
  }

  /**
   * TODO
   *
   * @param userVO
   * @return
   */
  fun getEntity(userVO: UserVO): UserEntity {
    val userEntity = UserEntity(
      username = userVO.username,
      password = passwordEncoder.encode(userVO.password),
      birthday = Instant.ofEpochMilli(userVO.birthday).atZone(ZoneId.systemDefault()).toLocalDate(),
      gender = GenderEnum.convert(userVO.gender),
      roles = userVO.roles,
      authorities = userVO.authorities
    )
    // when updated
    if (userVO.id != null) {
      userEntity.id = validateObjectId(userVO.id)
    }
    return userEntity
  }
}
