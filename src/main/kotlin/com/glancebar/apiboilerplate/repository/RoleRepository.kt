package com.glancebar.apiboilerplate.repository

import com.glancebar.apiboilerplate.entity.RoleEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
interface RoleRepository: MongoRepository<RoleEntity, ObjectId> {

}