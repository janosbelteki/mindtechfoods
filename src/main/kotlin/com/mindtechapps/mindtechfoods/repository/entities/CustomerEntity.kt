package com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "customers")
data class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    var id: Int? = null,
    @Column(name = "name")
    val name: String = "",
    @Column(name = "email")
    val email: String,
    @Column(name = "passwordHash")
    val passwordHash: String,
    @Column(name = "createdAt")
    val createdAt: Instant,
    @Column(name = "address")
    val address: String = ""
)