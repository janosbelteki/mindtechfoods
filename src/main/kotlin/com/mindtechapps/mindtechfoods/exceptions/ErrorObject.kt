package com.mindtechapps.com.mindtechapps.mindtechfoods.exceptions

class ErrorObject(
    val messages: MutableList<MutableMap<String, String>>
) {
    constructor(message: String) : this(mutableListOf(mutableMapOf("label" to message)))
}