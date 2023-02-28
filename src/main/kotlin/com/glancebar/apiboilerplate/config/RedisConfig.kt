package com.glancebar.apiboilerplate.aspect

import com.glancebar.apiboilerplate.utils.Log
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.scripting.support.ResourceScriptSource
import java.io.Serializable
import java.time.Duration

/**
 *
 * @author YISEN
 * @date 2020/12/21
 */
@Configuration
class RedisConfig(
  val connectionFactory: RedisConnectionFactory,
  val redisTemplate: StringRedisTemplate,
) {

  var testSha1: String? = null
  private final val scriptPath = "scripts"
  companion object : Log()

  /**
   * Custom redisTemplate, like object serializer
   *
   * @param connectionfactory
   * @return
   */
  @Bean
  fun redisTemplate(connectionfactory: JedisConnectionFactory): RedisTemplate<String, Serializable> {
    val redisTemplate = RedisTemplate<String, Serializable>()
    redisTemplate.keySerializer = StringRedisSerializer()
    redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer()
    redisTemplate.setConnectionFactory(connectionFactory)
    return redisTemplate
  }

  @Bean
  fun initRedisCacheManager(): RedisCacheManager {
    // Redis加锁写入器
    val writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory)
    // Redis缓存默认设置
    val config = RedisCacheConfiguration.defaultCacheConfig()
    config.serializeValuesWith(
      RedisSerializationContext.SerializationPair.fromSerializer(
        JdkSerializationRedisSerializer()
      )
    )
    config.entryTtl(Duration.ofMillis(10))
    config.disableKeyPrefix()
    return RedisCacheManager(writer, config)
  }

  /**
   * load lua script after start
   *
   */
  @PostConstruct
  fun loadRedisScript() {
    // load test.lua
    logger.info("hello error hello error~")
    val script = this.initScript("$scriptPath/test.lua")
    testSha1 = redisTemplate.connectionFactory?.connection?.scriptLoad(script.scriptAsString.toByteArray())
      ?: throw RuntimeException()

    // load other lua script
  }

  /**
   * Init script by given path
   *
   * @param path
   * @return
   */
  fun initScript(path: String): DefaultRedisScript<Any> {
    val addScript = DefaultRedisScript<Any>()
    addScript.setScriptSource(ResourceScriptSource(ClassPathResource(path)))
    addScript.resultType = Any::class.java
    return addScript
  }
}
