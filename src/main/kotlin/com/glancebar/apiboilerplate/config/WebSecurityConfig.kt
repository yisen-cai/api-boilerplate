package com.glancebar.apiboilerplate.config

import com.glancebar.apiboilerplate.auth.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * Basic web security config
 * @author Ethan Gary
 * @date 2020/12/15
 */
@EnableWebSecurity
class WebSecurityConfig(
    @Qualifier("passwordEncoder")
    val passwordEncoder: PasswordEncoder,
    @Qualifier("userDetailServiceImpl") val userDetailsService: UserDetailsService,
    val jwtAuthenticationProvider: JwtAuthenticationProvider,
    val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
    val customAuthenticationFailureHandler: CustomAuthenticationFailureHandler,
    val restAuthenticationEntryPoint: RestAuthenticationEntryPoint
) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity?) {
        // Enable CORS and disable CSRF
        val http1 = http!!.cors()
            .and()
            .csrf().disable()

        // Set session management to stateless
        val http2 = http1
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

        // Set unauthorized requests exception handler
        val http3 = http2
            .exceptionHandling()
            .authenticationEntryPoint(restAuthenticationEntryPoint)
            .and()

        http3.authorizeRequests()
            .antMatchers("/hello").hasAnyRole("USER")
            .antMatchers(
                "/auth/login",
                "/wechat/mini-program/login",
                "/auth/register"
            ).permitAll()
            .antMatchers("/roles/*", "/authorities/*").hasRole("ADMIN")
            .anyRequest().authenticated()

        http3
            .addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .authenticationProvider(jwtAuthenticationProvider)
            .exceptionHandling()
            .and()
            .httpBasic()
            .and()
            .formLogin()
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler)
//            .and()
//            .oauth2ResourceServer().jwt()
    }

//    override fun authenticationManager(): AuthenticationManager {
//        return super.authenticationManager()
//    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
//        val passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        auth!!.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }
}
