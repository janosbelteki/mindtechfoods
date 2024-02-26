package com.mindtechapps.com.mindtechapps.mindtechfoods.exceptions

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

data class MindtechAppsException(
    val messages: MutableList<String> = mutableListOf(),
    val httpStatus: HttpStatus
) : RuntimeException() {
    constructor(label: String, httpStatus: HttpStatus) : this(mutableListOf(label), httpStatus)
}