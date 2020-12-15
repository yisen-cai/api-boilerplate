package com.glancebar.apiboilerplate.exceptions

import org.springframework.validation.BindingResult


/**
 *
 * @author Ethan Gary
 * @date 2020/12/15
 */
class ParamsException(
    errMsg: String = "",
    errCode: Int = 1,
    val result: BindingResult
) :
    APIException(errMsg, errCode) {
}