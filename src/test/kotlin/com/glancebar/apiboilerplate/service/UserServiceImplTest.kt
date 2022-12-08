package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.repository.UserRepository
import com.glancebar.apiboilerplate.vo.UserVO
import org.bson.types.ObjectId
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset

/**
 *
 * @author YISEN
 * @date 2020/12/19
 */
@SpringBootTest
class UserServiceImplTest {

  @Autowired
  lateinit var userService: UserService

  @MockBean
  lateinit var userRepository: UserRepository

  final val userEntityUsername = "user"
  final val userEntityPassword = "password"
  final val userEntityBirthday: LocalDate = LocalDate.now()

  final val userEntity = UserEntity(
    id = ObjectId(),
    username = userEntityUsername,
    password = userEntityPassword,
    birthday = userEntityBirthday
  )
  val registerVO = UserVO()

  @Test
  internal fun registerUser() {
    registerVO.username = userEntityUsername
    registerVO.password = userEntityPassword
    registerVO.birthday = userEntityBirthday.toEpochSecond(LocalTime.NOON, ZoneOffset.UTC) * 1000
    val before = UserEntity(
      username = userEntityUsername,
      password = userEntityPassword,
      birthday = userEntityBirthday
    )

    Mockito.`when`(userRepository.save(before)).thenReturn(userEntity)
  }

  @Test
  internal fun getUser() {
  }
}
