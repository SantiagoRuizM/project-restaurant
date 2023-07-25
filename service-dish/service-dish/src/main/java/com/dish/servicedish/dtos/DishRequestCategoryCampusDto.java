package com.dish.servicedish.dtos;

public class DishRequestCategoryCampusDto {

    private Long category;
    private Long campus;

    public DishRequestCategoryCampusDto() {
    }

    public DishRequestCategoryCampusDto(Long category, Long campus) {
        this.category = category;
        this.campus = campus;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getCampus() {
        return campus;
    }

    public void setCampus(Long campus) {
        this.campus = campus;
    }
}
