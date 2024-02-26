package com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests

data class OrderRequest(
    val customerId: String,
    val restaurantId: String,
    val items: List<OrderItem>
)

data class OrderItem(
    //TODO: ehhez még létre kellene hozni egy megfelelő táblát a db-ben
    val itemId: Int,
    val quantity: Int,
    val specialInstructions: String? = ""
)