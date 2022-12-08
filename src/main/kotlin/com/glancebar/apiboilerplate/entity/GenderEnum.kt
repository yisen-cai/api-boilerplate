package com.glancebar.apiboilerplate.entity

import com.glancebar.apiboilerplate.exceptions.ParamsException
import com.glancebar.apiboilerplate.utils.ErrResult

/**
 * Gender enum
 * @author YISEN
 * @date 2020/12/15
 */
enum class GenderEnum(val value: String) {

  MALE("male"),
  FEMALE("female"),
  CROSS_GENDER("cross"),
  UNKNOWN("unknown");

  companion object {
    fun convert(p0: String): GenderEnum {
      for (gender in GenderEnum.values()) {
        if (gender.value == p0) {
          return gender
        }
      }
      throw ParamsException(errResult = ErrResult("性别错误", 1, null))
    }
  }
}
