package com.order.serviceorder.dtos.order;

import com.order.serviceorder.dtos.dish.DishForOrderDto;
import com.order.serviceorder.dtos.user.UserRequestDto;
import com.order.serviceorder.entities.UserEntity;

import java.util.List;

public class OrderRequestDto {

    private List<DishForOrderDto> dish;
    private Long campus;
    private Long user;

    public OrderRequestDto() {
    }

    public OrderRequestDto(List<DishForOrderDto> dish, Long campus, Long user) {
        this.dish = dish;
        this.campus = campus;
        this.user = user;
    }

    public List<DishForOrderDto> getDish() {
        return dish;
    }

    public void setDish(List<DishForOrderDto> dish) {
        this.dish = dish;
    }

    public Long getCampus() {
        return campus;
    }

    public void setCampus(Long campus) {
        this.campus = campus;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
