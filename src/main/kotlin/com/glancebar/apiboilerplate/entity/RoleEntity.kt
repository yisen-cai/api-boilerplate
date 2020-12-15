package com.glancebar.apiboilerplate.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
@Document(value = "role")
class RoleEntity(
    @Id
    var id: ObjectId = ObjectId(),
    var name: String,
    var description: String,
    var authorities: MutableSet<AuthorityEntity> = mutableSetOf()
)  {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RoleEntity) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}