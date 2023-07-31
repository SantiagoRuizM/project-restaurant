package com.claim.serviceclaim.externals;

public class CampusEntity {

    private Long id;
    private String name;

    public CampusEntity() {
    }

    public CampusEntity(Long id) {
        this.id = id;
    }

    public CampusEntity(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
