package com.glancebar.apiboilerplate.repository

import com.glancebar.apiboilerplate.entity.UserEntity
import org.bson.types.ObjectId
import org.springframework.data.repository.CrudRepository


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
interface UserRepository : CrudRepository<UserEntity, ObjectId> {

    /**
     * find top by username
     */
    fun findTopByUsernameEquals(username: String): UserEntity?
}