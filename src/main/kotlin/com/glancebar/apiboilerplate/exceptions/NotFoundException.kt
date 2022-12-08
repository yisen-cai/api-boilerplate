package com.glancebar.apiboilerplate.exceptions

import com.glancebar.apiboilerplate.utils.ErrResult

/**
 *
 * @author YISEN
 * @date 2020/12/19
 */
class NotFoundException(
  errResult: ErrResult
) : APIException(errResult)
