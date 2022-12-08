package com.glancebar.apiboilerplate.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

/**
 *
 * @author YISEN
 * @date 2020/12/15
 */
@Document(value = "authority")
data class AuthorityEntity(
  @Id
  var id: ObjectId? = null,
  var name: String,
  var description: String,
) : Serializable {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is AuthorityEntity) return false

    if (id != other.id) return false

    return true
  }

  override fun hashCode(): Int {
    return id?.hashCode() ?: 0
  }
}
