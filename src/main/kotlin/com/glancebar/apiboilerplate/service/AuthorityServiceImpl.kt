package com.glancebar.apiboilerplate.service

import com.glancebar.apiboilerplate.repository.AuthorityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 *
 * @author Ethan Gary
 * @date 2020/12/18
 */
@Service
class AuthorityServiceImpl(
    val authorityRepository: AuthorityRepository
) : AuthorityService {



}