package com.mindtechapps.com.mindtechapps.mindtechfoods.repository

import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities.RestaurantEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RestaurantRepository : JpaRepository<RestaurantEntity, Int> {
}