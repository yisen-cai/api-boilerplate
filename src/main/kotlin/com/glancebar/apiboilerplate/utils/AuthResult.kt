package com.glancebar.apiboilerplate.utils


/**
 *
 * @author Ethan Gary
 * @date 2021/1/29
 */
data class AuthResult(
    val accessToken: String,
    val expiration: Long
) {

}