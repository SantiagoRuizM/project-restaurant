package com.order.serviceorder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "employee_entity")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "employeeOrder")
    @JsonManagedReference
    @JsonIgnore
    private List<OrderEntity> orderEntities;

    public EmployeeEntity() {
    }

    public EmployeeEntity(Long id, String name, List<OrderEntity> orderEntities) {
        this.id = id;
        this.name = name;
        this.orderEntities = orderEntities;
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

    public List<OrderEntity> getOrderEntities() {
        return orderEntities;
    }

    public void setOrderEntities(List<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
    }
}
