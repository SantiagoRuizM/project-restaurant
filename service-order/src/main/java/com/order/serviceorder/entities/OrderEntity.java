package com.order.serviceorder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "order_entity")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "dishes", nullable = false)
    private String dishes;
    @Column(name = "campus", nullable = false)
    private Long campus;
    @Column(name = "state", nullable = false)
    private String state = "Pendiente";
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_order_id", nullable = false)
    @JsonBackReference
    private UserEntity userOrder;

    public OrderEntity() {
    }

    public OrderEntity(Long id, String dishes, Long campus, String state, UserEntity userOrder) {
        this.id = id;
        this.dishes = dishes;
        this.campus = campus;
        this.state = state;
        this.userOrder = userOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public Long getCampus() {
        return campus;
    }

    public void setCampus(Long campus) {
        this.campus = campus;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserEntity getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserEntity userOrder) {
        this.userOrder = userOrder;
    }
}
