package com.order.serviceorder.dtos.employee;

public class EmployeeResponseDto {

    private String name;

    public EmployeeResponseDto() {
    }

    public EmployeeResponseDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
