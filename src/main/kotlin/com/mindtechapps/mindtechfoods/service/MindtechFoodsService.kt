package com.mindtechapps.com.mindtechapps.mindtechfoods.service

import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.CustomerRegistrationDto
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.toCustomerEntity
import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.MindtechFoodsRepository
import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities.CustomerEntity
import org.slf4j.LoggerFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

interface MindtechFoodsService {
    fun customerRegistration(customerRegistration: CustomerRegistrationDto): String
}

@Service
class DefaultUserService(
    private val mindtechFoodsRepository: MindtechFoodsRepository
) : MindtechFoodsService {
    private val logger = LoggerFactory.getLogger(MindtechFoodsService::class.java)

    override fun customerRegistration(customerRegistration: CustomerRegistrationDto): String {
        val hashedPassword = hashPassword(customerRegistration.password)
        val customerEntity = customerRegistration.toCustomerEntity(hashedPassword)

        return mindtechFoodsRepository.save(customerEntity).id.toString()
    }

    private fun hashPassword(password: String): String {
        val salt = BCrypt.gensalt()
        return BCrypt.hashpw(password, salt)
    }
}