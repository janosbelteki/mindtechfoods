package com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses

class RestaurantEntityResponse (
    val id: Int,
    val name: String,
    val contact: String,
    val address: String,
    val menu: String //TODO: itt is bővíteni kell majd objectté
)