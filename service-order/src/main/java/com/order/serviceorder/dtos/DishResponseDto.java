package com.order.serviceorder.dtos;

import com.order.serviceorder.externals.CategoryEntity;

public class DishResponseDto {

    private String name;
    private Long price;
    private CategoryEntity category;
    private Integer quantity;

    public DishResponseDto() {
    }

    public DishResponseDto(String name, Long price, CategoryEntity category, Integer quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
