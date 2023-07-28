package com.order.serviceorder.dtos.order;

import com.order.serviceorder.dtos.dish.DishResponseDto;
import com.order.serviceorder.dtos.user.UserRequestDto;
import com.order.serviceorder.externals.CampusEntity;

import java.util.List;

public class OrderResponseDto {

    private List<DishResponseDto> dishes;
    private CampusEntity campus;
    private String state;
    private UserRequestDto user;

    public OrderResponseDto() {
    }

    public OrderResponseDto(String state, UserRequestDto user) {
        this.state = state;
        this.user = user;
    }

    public List<DishResponseDto> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishResponseDto> dishes) {
        this.dishes = dishes;
    }

    public CampusEntity getCampus() {
        return campus;
    }

    public void setCampus(CampusEntity campus) {
        this.campus = campus;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserRequestDto getUser() {
        return user;
    }

    public void setUser(UserRequestDto user) {
        this.user = user;
    }
}
