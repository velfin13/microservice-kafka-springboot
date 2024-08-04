package me.velfinvelasquez.notification_service.events;

import me.velfinvelasquez.notification_service.models.enums.OrderStatus;

public record OrderEvent(String orderNumber, int itemsCount, OrderStatus orderStatus) {
}