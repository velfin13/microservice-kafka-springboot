package me.velfinvelasquez.orders_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import me.velfinvelasquez.orders_service.models.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}