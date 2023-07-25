package com.dish.servicedish.dtos;

public class DishRequestDto {

    private String name;
    private Long price;
    private String description;
    private String urlImage;
    private Long category;
    private Long campus;
    private char role;

    public DishRequestDto() {
    }

    public DishRequestDto(String name, Long price, String description, String urlImage, Long category, Long campus, char role) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.urlImage = urlImage;
        this.category = category;
        this.campus = campus;
        this.role = role;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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

    public char getRole() {
        return role;
    }

    public void setRole(char role) {
        this.role = role;
    }
}
