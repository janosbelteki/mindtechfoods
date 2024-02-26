package com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests

import com.mindtechapps.com.mindtechapps.mindtechfoods.common.enums.OrderStatus

data class UpdateOrderRequest (
    val status: OrderStatus
)