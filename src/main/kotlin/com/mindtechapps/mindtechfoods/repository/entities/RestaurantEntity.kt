package com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities

import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses.RestaurantEntityResponse
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "restaurants")
data class RestaurantEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    var id: Int? = null,
    @Column(name = "name")
    val name: String = "",
    @Column(name = "contact")
    val contact: String,
    @Column(name = "address")
    val address: String = "",
    @Column(name = "menu")
    val menu: String //TODO: ez placeholder, ezt majd bővíteni kell rendes menü objectté
)

fun RestaurantEntity.toRestaurantEntityResponse(): RestaurantEntityResponse {
    return RestaurantEntityResponse(
        id = id!!,
        name = name,
        contact = contact,
        address = address,
        menu = menu
    )
}