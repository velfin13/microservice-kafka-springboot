package me.velfinvelasquez.orders_service.controllers;

import lombok.RequiredArgsConstructor;
import me.velfinvelasquez.orders_service.models.dtos.OrderRequest;
import me.velfinvelasquez.orders_service.models.dtos.OrderResponse;
import me.velfinvelasquez.orders_service.services.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        this.orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrders() {
        return this.orderService.getAllOrders();
    }
}