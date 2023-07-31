package com.order.serviceorder.dtos.order;

import java.time.LocalTime;

public class OrderTimeResponseDto {

    private Long orderId;
    private String timeOrder;

    public OrderTimeResponseDto() {
    }

    public OrderTimeResponseDto(Long orderId, String timeOrder) {
        this.orderId = orderId;
        this.timeOrder = timeOrder;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        this.timeOrder = timeOrder;
    }
}
