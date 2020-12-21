package com.glancebar.apiboilerplate.utils

import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import java.nio.charset.Charset


/**
 *
 * @author Ethan Gary
 * @date 2020/12/21
 */

/**
 * Create request header
 *
 * @param username
 * @param password
 * @return
 */
fun createRequestEntity(username: String, password: String): HttpEntity<String> {
    val headers = HttpHeaders()
    val auth = Base64.encodeBase64("$username:$password".toByteArray(Charset.forName("US-ASCII")))
    val authString = "Basic ${String(auth)}"
//        'Authorization: Basic ZXRoYW46MTIzNDU2'
    headers.add(
        "Authorization",
        authString
    )
    return HttpEntity<String>(headers)
}