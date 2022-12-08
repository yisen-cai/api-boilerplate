package com.glancebar.apiboilerplate.exceptions

import com.glancebar.apiboilerplate.utils.ErrResult

/**
 * Custom API layer exception, supply msg and code to indicate error details
 * @author YISEN
 * @date 2020/12/15
 */
open class APIException(var errResult: ErrResult) : RuntimeException()
