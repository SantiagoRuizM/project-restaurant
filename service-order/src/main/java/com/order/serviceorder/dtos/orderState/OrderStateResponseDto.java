package com.order.serviceorder.dtos.orderState;

import com.order.serviceorder.dtos.user.UserResponseDto;
import com.order.serviceorder.enums.StateEnum;

public class OrderStateResponseDto {

    private StateEnum state;
    private String timeState;

    public OrderStateResponseDto() {
    }

    public OrderStateResponseDto(StateEnum state, String timeState) {
        this.state = state;
        this.timeState = timeState;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public String getTimeState() {
        return timeState;
    }

    public void setTimeState(String timeState) {
        this.timeState = timeState;
    }
}
