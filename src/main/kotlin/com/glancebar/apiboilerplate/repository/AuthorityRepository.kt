package com.glancebar.apiboilerplate.repository

import com.glancebar.apiboilerplate.entity.AuthorityEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

/**
 *
 * @author YISEN
 * @date 2020/12/15
 */
interface AuthorityRepository : MongoRepository<AuthorityEntity, ObjectId> {

  fun findTopByIdEquals(id: ObjectId): AuthorityEntity?

  fun existsByNameEquals(name: String): Boolean

//    override fun findAll(pageable: Pageable): Page<AuthorityEntity>
}
