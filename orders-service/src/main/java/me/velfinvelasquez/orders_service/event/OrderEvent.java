package me.velfinvelasquez.orders_service.event;

import me.velfinvelasquez.orders_service.models.enums.OrderStatus;

public record OrderEvent(String orderNumber, int itemsCount, OrderStatus orderStatus) {
}
