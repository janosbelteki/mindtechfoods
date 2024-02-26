package com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests

import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities.CustomerEntity
import java.time.Instant
import java.util.*

data class CustomerRegistrationDto(
    val email: String,
    val password: String,
    val passwordAgain: String,
    val name: String,
    val address: String,
) {
    override fun toString(): String {
        return "email: $email, " +
                "name: $name, " +
                "address: $address, "
    }
}

fun CustomerRegistrationDto.toCustomerEntity(
    hashedPassword: String
): CustomerEntity {
    return CustomerEntity(
        createdAt = Instant.now(),
        email = email.lowercase(),
        name = name,
        passwordHash = hashedPassword,
        address = address,
    )
}