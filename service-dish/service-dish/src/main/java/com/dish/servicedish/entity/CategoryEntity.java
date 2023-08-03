package com.dish.servicedish.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

/**
 * This class represent the category of the dishes.
 * it contains information about: identifier of category, name of category and list of dishes in that category.
 * - JsonIgnoreProperties is used for ignore a specific characteristic of Jackson-Json conversion.
 *   - hibernateLazyInitializer is putting for manage the initialization of the data load until you needed.
 */
@Entity(name = "category_entity")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class CategoryEntity {

    /**
     * Unique identifier for every category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * String name identifier for every category.
     */
    private String name;
    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    @JsonIgnore
    private List<DishEntity> dishEntities;

    public CategoryEntity() {
    }

    /**
     * Constructor with all parameters
     */
    public CategoryEntity(Long id, String name, List<DishEntity> dishEntities) {
        this.id = id;
        this.name = name;
        this.dishEntities = dishEntities;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public List<DishEntity> getDishEntities() {return dishEntities;}

    public void setDishEntities(List<DishEntity> dishEntities) {this.dishEntities = dishEntities;}
}
