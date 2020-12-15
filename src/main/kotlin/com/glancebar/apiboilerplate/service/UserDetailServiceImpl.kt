package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.entity.RoleEntity
import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


/**
 * Implement UserDetailsService
 * @author Ethan Gary
 * @date 2020/12/15
 */
@Service
class UserDetailServiceImpl(val userRepository: UserRepository) : UserDetailsService {


    private fun getAuthorities(userEntity: UserEntity): List<GrantedAuthority> {
        val authorities: MutableSet<GrantedAuthority> = mutableSetOf()
        userEntity.roles.forEach { roleEntity: RoleEntity ->
            authorities.add(SimpleGrantedAuthority(roleEntity.name))
            roleEntity.authorities.forEach { authorityEntity ->
                authorities.add(SimpleGrantedAuthority(authorityEntity.name))
            }
        }
        return authorities.toList()
    }

    /**
     * load user method
     */
    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity: UserEntity? = userRepository.findTopByUsernameEquals(username)
        if (userEntity != null) {
            return User(userEntity.username, userEntity.password, getAuthorities(userEntity))
        }
        throw UsernameNotFoundException("Username not found!")
    }
}