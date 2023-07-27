package com.order.serviceorder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "user_entity")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "order_active", nullable = false)
    private boolean orderActive = false;
    @OneToMany(mappedBy = "userOrder") //Aqui si es muchas ordenes por usuario? N:1, Habia puesto la relacion entre ordenes y usuarios como 1:1 en el diagrama
    @JsonManagedReference
    @JsonIgnore
    private List<OrderEntity> orderEntities;

    public UserEntity() {
    }

    public UserEntity(Long id, String name, boolean orderActive) {
        this.id = id;
        this.name = name;
        this.orderActive = orderActive;
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

    public boolean isOrderActive() {
        return orderActive;
    }

    public void setOrderActive(boolean orderActive) {
        this.orderActive = orderActive;
    }
}
