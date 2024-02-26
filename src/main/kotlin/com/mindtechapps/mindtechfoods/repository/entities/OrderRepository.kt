package com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity, Int> {
    fun findAllByRestaurantId(restaurantId: String): List<OrderEntity>?
}