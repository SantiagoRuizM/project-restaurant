package com.order.serviceorder.dtos.dish;

import com.order.serviceorder.externals.CategoryEntity;

public class DishResponseDto {

    private Long id;
    private String name;
    private Long price;
    private CategoryEntity category;
    private Long quantity;

    public DishResponseDto() {
    }

    public DishResponseDto(Long id, String name, Long price, CategoryEntity category, Long quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
