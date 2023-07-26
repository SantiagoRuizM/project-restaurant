package com.order.serviceorder.dtos.user;

public class UserResponseDto {

    private String name;

    public UserResponseDto() {
    }

    public UserResponseDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
