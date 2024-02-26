package com.mindtechapps.com.mindtechapps.mindtechfoods.repository

import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MindtechFoodsRepository : JpaRepository<CustomerEntity, Int> {
}
