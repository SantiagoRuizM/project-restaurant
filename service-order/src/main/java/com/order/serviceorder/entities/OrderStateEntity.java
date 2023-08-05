package com.order.serviceorder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.order.serviceorder.enums.StateEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "order_state_entity")
public class OrderStateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "number_order", nullable = false)
    private Long numberOrder;
    @Column(name = "state", nullable = false)
    private StateEnum state;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_user", nullable = false)
    @JsonBackReference
    private UserEntity orderUser;
    @Column(name = "start_state", nullable = false)
    private LocalDateTime startState = LocalDateTime.now();
    @Column(name = "end_state")
    private LocalDateTime endState;

    public OrderStateEntity() {
    }

    public OrderStateEntity(Long numberOrder, StateEnum state, UserEntity orderUser, LocalDateTime endState) {
        this.numberOrder = numberOrder;
        this.state = state;
        this.orderUser = orderUser;
        this.endState = endState;
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

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public UserEntity getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(UserEntity orderUser) {
        this.orderUser = orderUser;
    }

    public LocalDateTime getStartState() {
        return startState;
    }

    public void setStartState(LocalDateTime startState) {
        this.startState = startState;
    }

    public LocalDateTime getEndState() {
        return endState;
    }

    public void setEndState(LocalDateTime endState) {
        this.endState = endState;
    }
}
