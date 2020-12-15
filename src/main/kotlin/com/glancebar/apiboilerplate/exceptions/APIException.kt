package com.glancebar.apiboilerplate.exceptions


/**
 * Custom API layer exception, supply msg and code to indicate error details
 * @author Ethan Gary
 * @date 2020/12/15
 */
open class APIException(val errMsg: String, val errCode: Int) : RuntimeException() {
}