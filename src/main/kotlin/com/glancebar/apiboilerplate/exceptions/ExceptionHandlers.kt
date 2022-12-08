package com.glancebar.apiboilerplate.exceptions

import com.glancebar.apiboilerplate.utils.ErrResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Exception handlers
 *
 * @constructor Create empty Exception handlers
 */
@ResponseBody
@ControllerAdvice
class ExceptionHandlers {

  /**
   * Handle API exceptions
   *
   * @param e
   * @return
   */
  @ExceptionHandler(APIException::class)
  fun handleAPIExceptions(e: APIException): ResponseEntity<ErrResult> {
    e.printStackTrace()
    return ErrResult.ResponseBuilder()
      .result(e.errResult)
      .message(e.message!!)
      .build()
  }

  /**
   *
   */
  @ExceptionHandler(WechatNonExistsException::class)
  fun handleWechatNonExistsException(e: WechatNonExistsException): ResponseEntity<ErrResult> {
    return ErrResult.ResponseBuilder()
      .result(e.errResult)
      .status(HttpStatus.CONFLICT)
      .build()
  }

  /**
   * Handle user exists exception
   *
   * @param e
   * @return
   */
  @ExceptionHandler(UsernameExistsException::class)
  fun handleUserExistsException(e: UsernameExistsException): ResponseEntity<ErrResult> {
    return ErrResult.ResponseBuilder()
      .result(e.errResult)
      .status(HttpStatus.CONFLICT)
      .build()
  }

  /**
   * Handle not found exception
   *
   * @param e
   * @return
   */
  @ExceptionHandler(NotFoundException::class)
  fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrResult> {
    return ErrResult.ResponseBuilder()
      .result(e.errResult)
      .status(HttpStatus.NOT_FOUND)
      .build()
  }

  /**
   * Handle params exception
   *
   * @param e
   * @return
   */
  @ExceptionHandler(ParamsException::class)
  fun handleParamsException(e: ParamsException): ResponseEntity<ErrResult> {
    e.printStackTrace()
    return ErrResult.ResponseBuilder()
      .result(e.errResult)
      .status(HttpStatus.BAD_REQUEST)
      .build()
  }

  /**
   * Handle exceptions
   *
   * @param e
   * @return
   */
  @ExceptionHandler(RuntimeException::class)
  fun handleExceptions(e: RuntimeException): ResponseEntity<ErrResult> {
    e.printStackTrace()
    return ErrResult.ResponseBuilder()
      .message(e.message!!)
      .build()
  }

  /**
   * Handle validation exception
   *
   * @param e
   * @return
   */
  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handleValidationException(e: MethodArgumentNotValidException): ResponseEntity<ErrResult> {
    return ErrResult.ResponseBuilder()
      .status(HttpStatus.BAD_REQUEST)
      .message("参数错误")
      .details(details = e.message).build()
  }

  /**
   * Handle http message not readable exception
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(HttpMessageNotReadableException::class)
  fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrResult> {
    return ErrResult.ResponseBuilder().build()
  }
}
