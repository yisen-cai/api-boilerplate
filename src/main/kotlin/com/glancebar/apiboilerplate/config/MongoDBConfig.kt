package com.glancebar.apiboilerplate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 * @author YISEN
 * @date 2020/12/18
 */
@Configuration
class MongoDBConfig {

  @Bean
  fun userCascadingMongoEventListener(): UserCascadeSaveMongoListener {
    return UserCascadeSaveMongoListener()
  }
}
