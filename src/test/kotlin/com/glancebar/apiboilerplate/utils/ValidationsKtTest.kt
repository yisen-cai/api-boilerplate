package com.glancebar.apiboilerplate.utils

import com.glancebar.apiboilerplate.exceptions.ParamsException
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class ValidationsKtTest {

  @Test
  fun invalidIdString() {
    val invalidIdString = "123"
    assertThrows(ParamsException::class.java) {
      validateObjectId(invalidIdString)
    }
  }

  @Test
  internal fun validIdString() {
    val validIdString = ObjectId().toString()
    assertDoesNotThrow {
      validateObjectId(validIdString)
    }
  }

  @Test
  internal fun nullIdString() {
    val nullIdString: String? = null
    assertThrows(ParamsException::class.java) {
      validateObjectId(nullIdString)
    }
  }

  @Test
  internal fun emptyIdString() {
    val emptyIdString = ""
    assertThrows(ParamsException::class.java) {
      validateObjectId(emptyIdString)
    }
  }
}
