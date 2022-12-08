package com.glancebar.apiboilerplate.repository

import com.glancebar.apiboilerplate.dto.UserDTO
import com.glancebar.apiboilerplate.entity.UserEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

/**
 *
 * @author YISEN
 * @date 2020/12/15
 */
interface UserRepository : MongoRepository<UserEntity, ObjectId> {

  fun findTopById(id: ObjectId): UserEntity?

  @Query(value = "{ '_class' : 'com.glancebar.apiboilerplate.dto.UserDTO' }")
  fun findTopByWechatOpenIdEquals(openId: String): UserDTO?

  /**
   * find top by username
   */
  fun findTopByUsernameEquals(username: String): UserEntity?

  fun existsByUsernameEquals(username: String): Boolean

  @Query(value = "{ '_class' : 'com.glancebar.apiboilerplate.dto.UserDTO' }")
  fun findTopByIdEquals(id: ObjectId): UserDTO?

//    fun save(userEntity: UserEntity): UserEntity
}
