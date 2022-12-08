package com.glancebar.apiboilerplate.config

import com.glancebar.apiboilerplate.filter.TimeFilter
import com.glancebar.apiboilerplate.interceptor.TimeInterceptor
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
@Configuration
class WebConfig(
  val timeInterceptor: TimeInterceptor
) : WebMvcConfigurer {

  @Bean
  fun timeFilter(): FilterRegistrationBean<*> {
    val registrationBean: FilterRegistrationBean<TimeFilter> = FilterRegistrationBean()
    registrationBean.filter = TimeFilter()
    // add url patterns
    registrationBean.addUrlPatterns("/hello")
    return registrationBean
  }

  override fun addInterceptors(registry: InterceptorRegistry) {
    registry.addInterceptor(timeInterceptor)
  }

  override fun addCorsMappings(registry: CorsRegistry) {
    super.addCorsMappings(registry)
//        registry
//            .addMapping("/**")
//            .allowedOrigins("*")
//            .allowedMethods("*")
//            .allowedHeaders("*")
//            .allowCredentials(false).maxAge(3600)
  }

  /**
   * <a href="https://spring.io/blog/2015/06/08/cors-support-in-spring-framework">
   */
//    @Bean
  fun corsFilter(): FilterRegistrationBean<*> {
    val source = UrlBasedCorsConfigurationSource()
    val config = CorsConfiguration()
    config.allowCredentials = true
    config.addAllowedOrigin("*")
    config.addAllowedMethod("*")
    config.addAllowedHeader("*")
    source.registerCorsConfiguration("/**", config)
    val bean = FilterRegistrationBean(CorsFilter(source))
    bean.order = 0
    return bean
  }
}
