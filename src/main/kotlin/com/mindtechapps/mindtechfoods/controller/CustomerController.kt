package com.mindtechapps.com.mindtechapps.mindtechfoods.controller

import com.mindtechapps.com.mindtechapps.mindtechfoods.common.API_PATH
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.CustomerRegistrationDto
import com.mindtechapps.com.mindtechapps.mindtechfoods.exceptions.ExceptionHandler
import com.mindtechapps.com.mindtechapps.mindtechfoods.service.MindtechFoodsService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping(API_PATH)
class CustomerController(
    private val mindtechFoodsService: MindtechFoodsService
) : ExceptionHandler() {
    private val logger = LoggerFactory.getLogger(CustomerController::class.java)

    @PostMapping(value = ["/auth/registration"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun customerRegistration(
        @RequestBody customerRegistration: CustomerRegistrationDto
    ): ResponseEntity<String> {
        logger.info("Customer registration info, customerRegistration: $customerRegistration")
        val userId = mindtechFoodsService.customerRegistration(customerRegistration)
        return ResponseEntity.ok().body(userId)
    }


}