package com.glancebar.apiboilerplate.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent
import org.springframework.stereotype.Component

/**
 *
 * @author YISEN
 * @date 2020/12/18
 */
@Component
class UserCascadeSaveMongoListener() : AbstractMongoEventListener<Any>() {

  @Autowired
  lateinit var mongoOperations: MongoOperations

  override fun onBeforeConvert(event: BeforeConvertEvent<Any>) {
    var source = event.source
//        if ((source is UserEntity)&& (source as UserEntity).) {
//
//            }
  }
}
