package com.glancebar.apiboilerplate.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
@Document(value = "authority")
class AuthorityEntity(
    @Id
    var id:ObjectId = ObjectId(),
    var name: String,
    var description: String
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AuthorityEntity

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}