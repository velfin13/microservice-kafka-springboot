package me.velfinvelasquez.orders_service.controllers;

import lombok.RequiredArgsConstructor;
import me.velfinvelasquez.orders_service.models.dtos.OrderRequest;
import me.velfinvelasquez.orders_service.models.dtos.OrderResponse;
import me.velfinvelasquez.orders_service.services.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "orders-service", fallbackMethod = "placeOrderFallback")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        var orders = this.orderService.placeOrder(orderRequest);
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrders() {
        return this.orderService.getAllOrders();
    }

    private ResponseEntity<OrderResponse> placeOrderFallback(OrderRequest orderRequest, Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}