package com.order.serviceorder.dtos;

import com.order.serviceorder.entities.CampusEntity;
import com.order.serviceorder.entities.CategoryEntity;

public class DishResponseDto {

    private String name;
    private Long price;
    private CategoryEntity category;
    private CampusEntity campus;
    private Integer quantity;

    public DishResponseDto() {
    }

    public DishResponseDto(String name, Long price, CategoryEntity category, CampusEntity campus, Integer quantity) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.campus = campus;
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

    public CampusEntity getCampus() {
        return campus;
    }

    public void setCampus(CampusEntity campus) {
        this.campus = campus;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}