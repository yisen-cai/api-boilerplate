package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.entity.GenderEnum
import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.exceptions.ParamsException
import com.glancebar.apiboilerplate.repository.UserRepository
import com.glancebar.apiboilerplate.utils.*
import com.glancebar.apiboilerplate.vo.UserVO
import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.net.URI
import java.time.Instant
import java.time.ZoneId


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
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