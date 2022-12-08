package com.glancebar.apiboilerplate.exceptions

import com.glancebar.apiboilerplate.utils.ErrResult

/**
 *
 * @author YISEN
 * @date 2020/12/15
 */
class ParamsException(
  errResult: ErrResult = ErrResult("参数错误", 1, null)
) :
  APIException(errResult)
