package com.glancebar.apiboilerplate.exceptions

import com.glancebar.apiboilerplate.utils.ErrResult

class WechatNonExistsException(
  errResult: ErrResult
) : APIException(errResult)
