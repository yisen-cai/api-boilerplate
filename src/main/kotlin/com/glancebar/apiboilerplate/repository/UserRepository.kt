package com.glancebar.apiboilerplate.repository

import com.glancebar.apiboilerplate.entity.UserEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
interface UserRepository : MongoRepository<UserEntity, ObjectId> {

    /**
     * find top by username
     */
    fun findTopByUsernameEquals(username: String): UserEntity?

    fun existsByUsernameEquals(username: String): Boolean

//    fun save(userEntity: UserEntity): UserEntity
}