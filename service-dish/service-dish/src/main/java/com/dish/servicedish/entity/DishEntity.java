package com.dish.servicedish.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


/**
 * This class represents the Dish entity, which corresponds to a single recipe.
 * It contains information about the name, price, description, image URL, category, and status of the dish.
 */
@Entity(name = "dish_entity")
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id; 
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private Long price;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "url_image", nullable = false)
    private String urlImage;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category", nullable = false)
    @JsonBackReference
    private CategoryEntity category;
    @Column(name = "active", nullable = false)
    private boolean active = true;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "campus", nullable = false)
    @JsonBackReference
    private CampusEntity campus;

    public DishEntity() {
    }

    public DishEntity(Long id, String name, Long price, String description, String urlImage, CategoryEntity category, boolean active, CampusEntity campus) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.urlImage = urlImage;
        this.category = category;
        this.active = active;
        this.campus = campus;
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CampusEntity getCampus() {
        return campus;
    }

    public void setCampus(CampusEntity campus) {
        this.campus = campus;
    }
}
