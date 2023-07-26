package com.order.serviceorder.dtos.user;

public class UserRequestDto {

    private String name;

    public UserRequestDto() {
    }

    public UserRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
