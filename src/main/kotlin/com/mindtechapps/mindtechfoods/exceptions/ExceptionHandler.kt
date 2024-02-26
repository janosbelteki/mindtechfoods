package com.mindtechapps.com.mindtechapps.mindtechfoods.exceptions

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [(MindtechAppsException::class)])
    protected fun handleOwnException(e: MindtechAppsException): ResponseEntity<ErrorObject> {
        e.printStackTrace()
        return ResponseEntity.status(e.httpStatus).body(ErrorObject(e.messages.first()))
    }
}