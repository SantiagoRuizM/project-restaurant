package com.order.serviceorder.dtos.orderState;

import com.order.serviceorder.dtos.user.UserResponseDto;

public class OrderStateResponseDto {

    private String state;
    private String timeState;

    public OrderStateResponseDto() {
    }

    public OrderStateResponseDto(String state, String timeState) {
        this.state = state;
        this.timeState = timeState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimeState() {
        return timeState;
    }

    public void setTimeState(String timeState) {
        this.timeState = timeState;
    }
}
