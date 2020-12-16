package com.glancebar.apiboilerplate.exceptions

import com.glancebar.apiboilerplate.utils.ErrResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
        return ErrResult.ResponseBuilder().result(e.errResult).build()
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
        return ErrResult.ResponseBuilder().message(e.message!!).build()
    }
}