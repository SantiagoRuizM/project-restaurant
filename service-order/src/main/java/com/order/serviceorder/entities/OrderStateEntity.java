package com.order.serviceorder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "order_state_entity")
public class OrderStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "number_order", nullable = false)
    private Long numberOrder;
    @Column(name = "state", nullable = false)
    private String state;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_user", nullable = false)
    @JsonBackReference
    private UserEntity orderUser;

    public OrderStateEntity() {
    }

    public OrderStateEntity(Long numberOrder, String state, UserEntity userOrder) {
        this.numberOrder = numberOrder;
        this.state = state;
        this.orderUser = userOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(Long numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserEntity getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(UserEntity orderUser) {
        this.orderUser = orderUser;
    }
}
