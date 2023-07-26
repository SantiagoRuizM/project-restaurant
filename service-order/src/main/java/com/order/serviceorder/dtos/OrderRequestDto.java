package com.order.serviceorder.dtos;

import java.util.List;

public class OrderRequestDto {

    private List<DishForOrderDto> dish;
    private Long campus;

    public OrderRequestDto() {
    }

    public OrderRequestDto(List<DishForOrderDto> dish, Long campus) {
        this.dish = dish;
        this.campus = campus;
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
}
