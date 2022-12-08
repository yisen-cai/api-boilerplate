package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.entity.RoleEntity
import com.glancebar.apiboilerplate.entity.UserEntity
import com.glancebar.apiboilerplate.repository.UserRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Service

/**
 * Implement UserDetailsService
 * @author YISEN
 * @date 2020/12/15
 */
@Service
class UserDetailServiceImpl(val userRepository: UserRepository) : UserDetailsManager {

  /**
   * load user method
   */
  @Cacheable(value = ["redisCache"], key = "'auth:user:' + #username")
  override fun loadUserByUsername(username: String): UserDetails {
    val userEntity: UserEntity? = userRepository.findTopByUsernameEquals(username)
    if (userEntity != null) {
//            return User
//                .withUsername(userEntity.username)
//                .password(userEntity.password)
//                .disabled(false)
//                .authorities(getAuthorities(userEntity))
//                .accountExpired(false)
//                .build()
      return User(
        userEntity.username,
        userEntity.password,
        userEntity.isActive,
        true,
        true,
        !userEntity.isDelete,
        getAuthorities(userEntity)
      )
    }
    throw UsernameNotFoundException("Username not found!")
  }

  override fun createUser(user: UserDetails?) {
    TODO("Not yet implemented")
  }

  override fun updateUser(user: UserDetails?) {
    TODO("Not yet implemented")
  }

  override fun deleteUser(username: String?) {
    TODO("Not yet implemented")
  }

  override fun changePassword(oldPassword: String?, newPassword: String?) {
    TODO("Not yet implemented")
  }

  override fun userExists(username: String?): Boolean {
    TODO("Not yet implemented")
  }

  /**
   * Get all authorities of user
   */
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
}
