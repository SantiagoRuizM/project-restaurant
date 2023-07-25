package com.dish.servicedish.dtos;

public class DishUpdateRequestDto {

    private Long price;
    private String description;
    private Long campus;

    public DishUpdateRequestDto() {
    }

    public DishUpdateRequestDto(Long price, String description, Long campus) {
        this.price = price;
        this.description = description;
        this.campus = campus;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCampus() {
        return campus;
    }

    public void setCampus(Long campus) {
        this.campus = campus;
    }
}
