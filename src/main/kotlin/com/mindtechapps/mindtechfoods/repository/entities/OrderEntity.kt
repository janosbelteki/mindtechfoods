package com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities

import com.mindtechapps.com.mindtechapps.mindtechfoods.common.enums.OrderStatus
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.OrderItem
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses.OrderResponse
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    var id: Int? = null,
    @Column(name = "customerId")
    val customerId: String,
    @Column(name = "restaurantId")
    val restaurantId: String,
    //TODO: ide az items objectet még implementálni kell
    //val items: List<OrderItem>
    @Column(name = "createdAt")
    var createdAt: Instant,
    //TODO: ez az enumot egyelőre nem fogadja el, ennek utána kell nézni még
    /*@Column(name = "status"),
    var status: OrderStatus*/
)

fun OrderEntity.toOrderResponse(): OrderResponse {
    return OrderResponse(
        customerId =  customerId,
        items = listOf(), //TODO: ez csak placeholder az implementációig
        createdAt = createdAt
    )
}