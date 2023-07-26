package com.order.serviceorder.dtos;

import com.order.serviceorder.externals.CampusEntity;

import java.util.List;

public class OrderResponseDto {

    private List<DishResponseDto> dishes;
    private CampusEntity campus;
    private String state;

    public OrderResponseDto() {
    }

    public OrderResponseDto(List<DishResponseDto> dishes, CampusEntity campus, String state) {
        this.dishes = dishes;
        this.campus = campus;
        this.state = state;
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
}
