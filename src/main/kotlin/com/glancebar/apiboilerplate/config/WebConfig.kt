package com.glancebar.apiboilerplate.config

import com.glancebar.apiboilerplate.TimeFilter
import com.glancebar.apiboilerplate.interceptor.TimeInterceptor
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 *
 * @author Ethan Gary
 * @date 2020/12/16
 */
@Configuration
class WebConfig(
    val timeInterceptor: TimeInterceptor,
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
}