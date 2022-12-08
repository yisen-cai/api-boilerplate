package com.glancebar.apiboilerplate.repository

import com.glancebar.apiboilerplate.entity.RoleEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

/**
 *
 * @author YISEN
 * @date 2020/12/15
 */
interface RoleRepository : MongoRepository<RoleEntity, ObjectId> {

  fun findTopByIdEquals(id: ObjectId): RoleEntity?

  fun existsByNameEquals(name: String): Boolean

//    fun deleteByIdEquals(id: ObjectId): Unit

  // Page<T> findAll(Pageable pageable);
//    override fun findAll(pageable: Pageable): Page<RoleEntity>
}
