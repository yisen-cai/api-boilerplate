package com.glancebar.apiboilerplate.exceptions

import com.glancebar.apiboilerplate.utils.ErrResult

/**
 *
 * @author YISEN
 * @date 2020/12/16
 */
class UsernameExistsException(
  errResult: ErrResult = ErrResult("用户名已存在", 1, null)
) : APIException(errResult)
