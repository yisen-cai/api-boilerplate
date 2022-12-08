package com.glancebar.apiboilerplate.utils

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 * Error Result body, supply error message and code to track issue.
 * @author YISEN
 * @date 2020/12/16
 */
data class ErrResult(
  var errMsg: String,
  var errCode: Int = 1,
  var details: Any? = null,
) {

  /**
   * There we use Builder pattern.
   * In kotlin, internal class default static class.
   * @property result
   * @property headers
   * @property httpStatus
   */
  class ResponseBuilder(
    var result: ErrResult = ErrResult("ERROR", -1, null),
    var headers: HttpHeaders = HttpHeaders(),
    var httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
  ) {
    fun result(result: ErrResult): ResponseBuilder {
      this.result = result
      return this
    }

    fun message(errMsg: String): ResponseBuilder {
      result.errMsg = errMsg
      return this
    }

    fun details(details: Any?): ResponseBuilder {
      result.details = details
      return this
    }

    fun code(errCode: Int): ResponseBuilder {
      result.errCode = errCode
      return this
    }

    fun header(headers: HttpHeaders): ResponseBuilder {
      this.headers = headers
      return this
    }

    fun status(status: HttpStatus): ResponseBuilder {
      httpStatus = status
      return this
    }

    /**
     * Build ResponseEntity with those information it's collected
     *
     * @return
     */
    fun build(): ResponseEntity<ErrResult> {
      return ResponseEntity<ErrResult>(result, headers, httpStatus)
    }
  }
}
