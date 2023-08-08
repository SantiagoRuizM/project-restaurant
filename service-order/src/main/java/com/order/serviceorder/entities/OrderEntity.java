package com.order.serviceorder.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.order.serviceorder.enums.StateEnum;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "order_entity")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany(mappedBy = "orderDish")
    @JsonManagedReference
    @JsonIgnore
    private List<OrderDishDetailsEntity> ordersDish;
    @Column(name = "campus", nullable = false)
    private Long campus;
    @Column(name = "state", nullable = false)
    private StateEnum state = StateEnum.EARRING;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_order_id", nullable = false)
    @JsonBackReference
    private UserEntity userOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_order_id")
    @JsonBackReference
    private EmployeeEntity employeeOrder = null;
    @Column(name = "delivery_id")
    private String deliveryId = null;
    @Column(name = "start_order")
    private LocalDateTime startOrder = LocalDateTime.now();
    @Column(name = "end_order")
    private LocalDateTime endOrder = null;

    public OrderEntity() {
    }

    public OrderEntity(Long id, List<OrderDishDetailsEntity> ordersDish, Long campus, StateEnum state, UserEntity userOrder, EmployeeEntity employeeOrder, String deliveryId, LocalDateTime startOrder, LocalDateTime endOrder) {
        this.id = id;
        this.ordersDish = ordersDish;
        this.campus = campus;
        this.state = state;
        this.userOrder = userOrder;
        this.employeeOrder = employeeOrder;
        this.deliveryId = deliveryId;
        this.startOrder = startOrder;
        this.endOrder = endOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderDishDetailsEntity> getOrdersDish() {
        return ordersDish;
    }

    public void setOrdersDish(List<OrderDishDetailsEntity> ordersDish) {
        this.ordersDish = ordersDish;
    }

    public Long getCampus() {
        return campus;
    }

    public void setCampus(Long campus) {
        this.campus = campus;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public UserEntity getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserEntity userOrder) {
        this.userOrder = userOrder;
    }

    public EmployeeEntity getEmployeeOrder() {
        return employeeOrder;
    }

    public void setEmployeeOrder(EmployeeEntity employeeOrder) {
        this.employeeOrder = employeeOrder;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public LocalDateTime getStartOrder() {
        return startOrder;
    }

    public void setStartOrder(LocalDateTime startOrder) {
        this.startOrder = startOrder;
    }

    public LocalDateTime getEndOrder() {
        return endOrder;
    }

    public void setEndOrder(LocalDateTime endOrder) {
        this.endOrder = endOrder;
    }
}
