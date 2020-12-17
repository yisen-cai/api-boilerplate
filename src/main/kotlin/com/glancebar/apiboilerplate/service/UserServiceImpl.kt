package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.entity.GenderEnum
import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.exceptions.UsernameExistsException
import com.glancebar.apiboilerplate.repository.UserRepository
import com.glancebar.apiboilerplate.utils.ErrResult
import com.glancebar.apiboilerplate.utils.Log
import com.glancebar.apiboilerplate.utils.OkResult
import com.glancebar.apiboilerplate.vo.RegisterVO
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
    val passwordEncoder: PasswordEncoder
) : UserService {

    companion object : Log()

    /**
     * Using given param to register a user
     */
    override fun registerUser(user: RegisterVO): ResponseEntity<OkResult> {
        if (userRepository.existsByUsernameEquals(user.username)) {
            throw UsernameExistsException(ErrResult("${user.username} exists", 1, null))
        }

        val userEntity = UserEntity(
            username = user.username,
            password = passwordEncoder.encode(user.password),
            birthday = Instant.ofEpochMilli(user.birthday).atZone(ZoneId.systemDefault()).toLocalDate(),
            gender = GenderEnum.convert(user.gender),
            roles = user.roles,
            authorities = user.authorities
        )

        val result = userRepository.save(userEntity)

        logger.info("User $result registered")

        val resultURI = URI("/users/${result.id}")

        // when entity was created, should return created status, and should not return relative info, return and id better
        return ResponseEntity
            .created(resultURI)
            .body(OkResult(msg = "User: `${result.username}` created", location = resultURI))
    }
}