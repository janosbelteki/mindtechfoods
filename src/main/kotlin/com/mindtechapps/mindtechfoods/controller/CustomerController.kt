package com.mindtechapps.com.mindtechapps.mindtechfoods.controller

import com.mindtechapps.com.mindtechapps.mindtechfoods.common.API_PATH
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.CustomerRegistrationDto
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.LoginRequest
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.OrderRequest
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.requests.UpdateOrderRequest
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses.LoginResponse
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses.MenuResponse
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses.OrderResponse
import com.mindtechapps.com.mindtechapps.mindtechfoods.controller.dtos.responses.RestaurantEntityResponse
import com.mindtechapps.com.mindtechapps.mindtechfoods.exceptions.ExceptionHandler
import com.mindtechapps.com.mindtechapps.mindtechfoods.service.MindtechFoodsService
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping(API_PATH)
class CustomerController(
    private val mindtechFoodsService: MindtechFoodsService
) : ExceptionHandler() {
    private val logger = LoggerFactory.getLogger(CustomerController::class.java)

    @PostMapping(value = ["/auth/registration"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun customerRegistration(
        @RequestBody customerRegistration: CustomerRegistrationDto
    ): ResponseEntity<String> {
        logger.info("Customer registration info, customerRegistration: $customerRegistration")
        val customerId = mindtechFoodsService.customerRegistration(customerRegistration)
        return ResponseEntity.ok().body(customerId)
    }

    @PostMapping(value = ["/auth/login"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun customerLogin(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<LoginResponse> {
        logger.info("Customer login, loginRequest email: ${loginRequest.email}")
        val loginResponse = mindtechFoodsService.customerLogin(loginRequest)
        return ResponseEntity.ok().body(loginResponse)
    }

    @GetMapping(value = ["/auth/who-am-i"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCustomerName(
        @RequestParam customerId: String
    ): ResponseEntity<String> {
        logger.info("Get customer name, customerId: $customerId")
        val customerName = mindtechFoodsService.getCustomerName(customerId)
        return ResponseEntity.ok().body(customerName)
    }

    @GetMapping(value = ["/restaurants"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRestaurants(
    ): ResponseEntity<List<RestaurantEntityResponse>> {
        logger.info("Get restaurants")
        val restaurants = mindtechFoodsService.getRestaurants()
        return ResponseEntity.ok().body(restaurants)
    }

    @GetMapping(value = ["/restaurants/{restaurantId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRestaurantById(
        @PathVariable("restaurantId") restaurantId: String,
    ): ResponseEntity<RestaurantEntityResponse> {
        logger.info("Get restaurant, restaurantId: $restaurantId")
        val restaurant = mindtechFoodsService.getRestaurant(restaurantId)
        return ResponseEntity.ok().body(restaurant)
    }

    @GetMapping(value = ["/restaurants/{restaurantId}/menu"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRestaurantMenu(
        @PathVariable("restaurantId") restaurantId: String,
    ): ResponseEntity<MenuResponse> {
        logger.info("Get restaurant menu, restaurantId: $restaurantId")
        val menu = mindtechFoodsService.getRestaurantMenu(restaurantId)
        return ResponseEntity.ok().body(menu)
    }

    @PostMapping(value = ["/orders"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun placeOrder(
        @RequestBody orderToPlace: OrderRequest
    ): ResponseEntity<String> {
        logger.info("Place order, orderToPlace: $orderToPlace")
        val orderId = mindtechFoodsService.placeOrder(orderToPlace)
        return ResponseEntity.ok().body(orderId)
    }


    //TODO: az étterem által hívható endpointoknál kelleni fog valami autentikáció/ellenőrzés, hogy biztos csak ők férjenek hozzá ezekhez
    @GetMapping(value = ["/orders"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrders(
        @RequestParam restaurantId: String,
    ): ResponseEntity<List<OrderResponse>> {
        logger.info("Get restaurant orders, restaurantId: $restaurantId")
        val menu = mindtechFoodsService.getRestaurantOrders(restaurantId)
        return ResponseEntity.ok().body(menu)
    }

    @PatchMapping(value = ["/orders/{orderId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateOrder(
        @PathVariable("orderId") orderId: String,
        @RequestBody updateOrder: UpdateOrderRequest
    ): ResponseEntity<Any?> {
        logger.info("Update order, orderId: $orderId")
        mindtechFoodsService.updateOrder(orderId, updateOrder)
        return ResponseEntity.ok().body(null)
    }

    @GetMapping(value = ["/orders/{orderId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getOrderDetails(
        @PathVariable("orderId") orderId: String,
    ): ResponseEntity<OrderResponse> {
        logger.info("Get order details, orderId: $orderId")
        val order = mindtechFoodsService.getOrderDetails(orderId)
        return ResponseEntity.ok().body(order)
    }
}