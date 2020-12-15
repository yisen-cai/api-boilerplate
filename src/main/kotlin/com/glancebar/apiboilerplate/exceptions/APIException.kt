package com.glancebar.apiboilerplate.exceptions


/**
 * Custom API layer exception, supply msg and code to indicate error details
 * @author Ethan Gary
 * @date 2020/12/15
 */
class APIException(val msg: String, val code: Int) : RuntimeException() {
}