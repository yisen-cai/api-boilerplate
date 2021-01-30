package com.glancebar.apiboilerplate.auth


/**
 *
 * @author Ethan Gary
 * @date 2021/1/28
 */
class JwtTokenMissingException(
    message: String
) : RuntimeException(message) {
}