package com.glancebar.apiboilerplate.exceptions

import com.glancebar.apiboilerplate.utils.ErrResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody


/**
 * Exception handler, handle all exceptions.
 * @author Ethan Gary
 * @date 2020/12/16
 */
@ResponseBody
@ControllerAdvice
class ExceptionHandlers {

    @ExceptionHandler(APIException::class)
    fun handleAPIExceptions(e: APIException): ResponseEntity<ErrResult> {
        e.printStackTrace()
        return ErrResult.ResponseBuilder()
            .result(e.errResult)
            .message(e.message!!)
            .build()
    }

    @ExceptionHandler(UsernameExistsException::class)
    fun handleUserExistsException(e: UsernameExistsException): ResponseEntity<ErrResult> {
        return ErrResult.ResponseBuilder()
            .result(e.errResult)
            .status(HttpStatus.CONFLICT)
            .build()
    }

    @ExceptionHandler(ParamsException::class)
    fun handleParamsException(e: ParamsException): ResponseEntity<ErrResult> {
        var detailsString = ""

        if (e.bindingResult != null) {
            val details: StringBuilder = StringBuilder()
            e.bindingResult.fieldErrors.forEach { fieldError ->
                details.append(fieldError.defaultMessage).append("\n")
            }
            detailsString = details.toString()
        }

        return ErrResult.ResponseBuilder().status(HttpStatus.BAD_REQUEST).result(e.errResult)
            .details(detailsString).build()
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleExceptions(e: RuntimeException): ResponseEntity<ErrResult> {
        e.printStackTrace()
        return ErrResult.ResponseBuilder()
            .message(e.message!!)
            .build()
    }


    /**
     * Handle Validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(e: MethodArgumentNotValidException): ResponseEntity<ErrResult> {
        val details: MutableMap<String, String> = HashMap()

        e.bindingResult.fieldErrors.forEach { error: FieldError ->
            details[error.field] = error.defaultMessage!!
        }

        return ErrResult.ResponseBuilder()
            .status(HttpStatus.BAD_REQUEST)
            .message("参数错误")
            .details(details = details).build()
    }


    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrResult> {
        return ErrResult.ResponseBuilder().build()
    }
}