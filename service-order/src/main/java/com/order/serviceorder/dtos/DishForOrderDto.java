package com.order.serviceorder.dtos;

public class DishForOrderDto {

    private Long idDish;
    private Long quantity;

    public DishForOrderDto() {
    }

    public DishForOrderDto(Long idDish, Long quantity) {
        this.idDish = idDish;
        this.quantity = quantity;
    }

    public Long getIdDish() {
        return idDish;
    }

    public void setIdDish(Long idDish) {
        this.idDish = idDish;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
