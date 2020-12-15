package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.dto.UserDTO
import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.repository.UserRepository
import com.glancebar.apiboilerplate.vo.RegisterVO
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDate


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
@Service
class UserServiceImpl(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) : UserService {

    override fun registerUser(user: RegisterVO): UserDTO {
        val userEntity = UserEntity(
            username = user.username,
            password = passwordEncoder.encode(user.password),
            birthday = LocalDate.ofEpochDay(user.birthday)
        )
        val result = userRepository.save(userEntity)
        return UserDTO(result.username)
    }
}