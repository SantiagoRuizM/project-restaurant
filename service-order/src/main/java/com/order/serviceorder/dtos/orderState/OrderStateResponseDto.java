package com.order.serviceorder.dtos.orderState;

import com.order.serviceorder.dtos.user.UserResponseDto;

public class OrderStateResponseDto {

    private Long numberOrder;
    private String state;
    private UserResponseDto user;
    private String timeState;

    public OrderStateResponseDto() {
    }

    public OrderStateResponseDto(Long numberOrder, String state, UserResponseDto user, String timeState) {
        this.numberOrder = numberOrder;
        this.state = state;
        this.user = user;
        this.timeState = timeState;
    }

    public Long getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(Long numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    public String getTimeState() {
        return timeState;
    }

    public void setTimeState(String timeState) {
        this.timeState = timeState;
    }
}
