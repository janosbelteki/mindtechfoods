package com.mindtechapps.com.mindtechapps.mindtechfoods.repository

import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<CustomerEntity, Int> {
    fun findByEmail(email: String): CustomerEntity?
}
