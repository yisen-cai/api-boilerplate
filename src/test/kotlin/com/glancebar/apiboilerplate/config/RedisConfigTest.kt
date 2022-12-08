package com.glancebar.apiboilerplate.config

import com.glancebar.apiboilerplate.aspect.RedisConfig
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.connection.ReturnType
import org.springframework.data.redis.core.StringRedisTemplate

/**
 * A redis lua script usage demo
 * @author YISEN
 * @date 2020/12/21
 */
@SpringBootTest
class RedisConfigTest(
  @Autowired
  val redisTemplate: StringRedisTemplate,
  @Autowired
  val redisConfig: RedisConfig,
) {

  private final val inputKey = "input"
  private final val resultKey = "result"
  private final val inputValue = 10

  @Test
  internal fun `Test redis lua script`() {
    val result = redisTemplate.connectionFactory!!.connection
      .evalSha<Long>(
        redisConfig.testSha1!!, ReturnType.fromJavaType(Long::class.java), 3,
        inputKey.toByteArray(),
        inputValue.toString().toByteArray(),
        resultKey.toByteArray()
      )?.toLong()
    assertEquals(11, result)
  }
}
