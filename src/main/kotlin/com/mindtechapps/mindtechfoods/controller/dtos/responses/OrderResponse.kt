package com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses

import java.time.Instant

data class OrderResponse(
    val customerId: String,
    val items: List<OrderItem>,
    val createdAt: Instant
)

data class OrderItem(
    //TODO: ehhez még létre kellene hozni egy megfelelő táblát a db-ben
    val itemId: Int,
    val quantity: Int,
    val specialInstructions: String? = ""
)