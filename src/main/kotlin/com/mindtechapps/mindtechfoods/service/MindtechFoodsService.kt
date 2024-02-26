package com.mindtechapps.com.mindtechapps.mindtechfoods.service

import com.mindtechapps.com.mindtechapps.mindtechfoods.common.CUSTOMER_ID_IS_NOT_IN_TABLE
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.CustomerRegistrationDto
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.LoginRequest
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.OrderRequest
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.UpdateOrderRequest
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.toCustomerEntity
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses.LoginResponse
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses.MenuResponse
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses.OrderResponse
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses.RestaurantEntityResponse
import com.mindtechapps.com.mindtechapps.mindtechfoods.exceptions.MindtechAppsException
import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.CustomerRepository
import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.RestaurantRepository
import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities.OrderRepository
import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities.toOrderResponse
import com.mindtechapps.com.mindtechapps.mindtechfoods.repository.entities.toRestaurantEntityResponse
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

interface MindtechFoodsService {
    fun customerRegistration(customerRegistration: CustomerRegistrationDto): String
    fun customerLogin(loginRequest: LoginRequest): LoginResponse
    fun getCustomerName(customerId: String): String
    fun getRestaurants(): List<RestaurantEntityResponse>
    fun getRestaurant(restaurantId: String): RestaurantEntityResponse
    fun getRestaurantMenu(restaurantId: String): MenuResponse
    fun placeOrder(orderToPlace: OrderRequest): String
    fun getRestaurantOrders(restaurantId: String): List<OrderResponse>
    fun updateOrder(orderId: String, updateOrder: UpdateOrderRequest)
    fun getOrderDetails(orderId: String): OrderResponse
}

@Service
class DefaultUserService(
    private val customerRepository: CustomerRepository,
    private val restaurantRepository: RestaurantRepository,
    private val orderRepository: OrderRepository
) : MindtechFoodsService {

    override fun customerRegistration(customerRegistration: CustomerRegistrationDto): String {
        val hashedPassword = hashPassword(customerRegistration.password)
        val customerEntity = customerRegistration.toCustomerEntity(hashedPassword)

        return customerRepository.save(customerEntity).id.toString()
    }

    override fun customerLogin(loginRequest: LoginRequest): LoginResponse {
        val customer = customerRepository.findByEmail(loginRequest.email.lowercase())
        val success = if (customer != null) {
            customer.passwordHash == hashPassword(loginRequest.password)
        } else {
            false
        }
        return LoginResponse(
            success = success
        )
    }

    override fun getCustomerName(customerId: String): String {
        val customer = customerRepository.findById(customerId.toInt()).orElse(null)
        return customer?.name ?: throw MindtechAppsException(CUSTOMER_ID_IS_NOT_IN_TABLE, HttpStatus.NOT_FOUND)
    }

    override fun getRestaurants(): List<RestaurantEntityResponse> {
        return restaurantRepository.findAll().map { it.toRestaurantEntityResponse() }
    }

    override fun getRestaurant(restaurantId: String): RestaurantEntityResponse {
        val restaurantEntity = restaurantRepository.findById(restaurantId.toInt()).orElse(null)
        return restaurantEntity.toRestaurantEntityResponse()
    }

    override fun getRestaurantMenu(restaurantId: String): MenuResponse {
        val restaurantEntity = restaurantRepository.findById(restaurantId.toInt()).orElse(null)
        return MenuResponse(restaurantEntity.menu)
    }

    override fun placeOrder(orderToPlace: OrderRequest): String {
        val restaurantEntity = restaurantRepository.findById(orderToPlace.restaurantId.toInt()).orElse(null)
        val customerEntity = customerRepository.findById(orderToPlace.customerId.toInt()).orElse(null)

        // TODO: itt a userhez logolni lenne érdemes az ordert, illetve az étteremhez is,
        //  meg valszeg kellene egy orders tábla, amibe gyűlnek maguk a rendelések, ebbe való elmentés után kapott id-t kellene visszaadnom
        //  az idő szűke miatt ez már nem fért bele sajnos
        return "ide jön az orderId"
    }

    override fun getRestaurantOrders(restaurantId: String): List<OrderResponse> {
        return orderRepository.findAllByRestaurantId(restaurantId)?.map { it.toOrderResponse() } ?: listOf()
    }

    override fun updateOrder(orderId: String, updateOrder: UpdateOrderRequest) {
        // TODO: itt patchelni kell a meglévő order entity-t, hogy a status field frissüljön rajta
    }

    override fun getOrderDetails(orderId: String): OrderResponse {
        return orderRepository.findById(orderId.toInt()).orElse(null).toOrderResponse()
    }

    private fun hashPassword(password: String): String {
        val salt = BCrypt.gensalt()
        return BCrypt.hashpw(password, salt)
    }
}