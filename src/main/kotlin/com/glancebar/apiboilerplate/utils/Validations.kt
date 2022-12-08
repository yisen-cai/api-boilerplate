package com.glancebar.apiboilerplate.utils

import com.glancebar.apiboilerplate.exceptions.NotFoundException
import com.glancebar.apiboilerplate.exceptions.ParamsException
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.springframework.data.repository.CrudRepository

/**
 * validate passed id is a valid ObjectId Hex
 * Accept nullable
 * @param objectId
 * @return
 */
fun validateObjectId(objectId: String?): ObjectId {
  if (objectId == null || objectId.isBlank() || !ObjectId.isValid(objectId)) {
    throw ParamsException(ErrResult("`$objectId` is not a valid ObjectId"))
  }
  return ObjectId(objectId)
}

/**
 * Universal method for each service check entity existence
 *
 * @param T
 * @param idString
 * @param repository
 * @param logger
 * @return ObjectId
 */
inline fun <reified T> verifyEntityExists(
  idString: String?,
  repository: CrudRepository<T, ObjectId>,
  logger: Logger,
): ObjectId {
  val objectId = validateObjectId(idString)
  if (!repository.existsById(objectId)) {
    val entityName = T::class.java.simpleName.replace("Entity", "")
    // T::class.java.name is with package path and plus class name
    val msg = "$entityName id=`$idString` does not exists"
    logger.debug(msg)
    throw NotFoundException(ErrResult(msg))
  }
  return objectId
}
