package com.mindtechapps.com.mindtechapps.mindtechfoods.common.enums

enum class OrderStatus(val type: String) {
    RECEIVED("received"),
    PREPARING("preparing"),
    READY("ready"),
    DELIVERED("delivered")
}