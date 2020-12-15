package com.glancebar.apiboilerplate.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class WebSecurityConfig(
    val passwordEncoder: PasswordEncoder,
    @Qualifier("userDetailServiceImpl") val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {


    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable()
            .authorizeRequests()
            .antMatchers("/hello").hasRole("USER")
            .antMatchers("/users/login", "/users/register").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic()
    }


    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }
}
