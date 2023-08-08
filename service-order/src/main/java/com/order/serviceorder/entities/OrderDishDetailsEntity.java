package com.order.serviceorder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "order_dish_details_entity")
public class OrderDishDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "quantity", nullable = false)
    private Long quantity;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_dish", nullable = false)
    @JsonBackReference
    private OrderEntity orderDish;
    @Column(name = "dish", nullable = false)
    private Long dish;

    public OrderDishDetailsEntity() {
    }

    public OrderDishDetailsEntity(Long quantity, OrderEntity orderDish, Long dish) {
        this.quantity = quantity;
        this.orderDish = orderDish;
        this.dish = dish;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public OrderEntity getOrderDish() {
        return orderDish;
    }

    public void setOrderDish(OrderEntity orderDish) {
        this.orderDish = orderDish;
    }

    public Long getDish() {
        return dish;
    }

    public void setDish(Long dish) {
        this.dish = dish;
    }
}
