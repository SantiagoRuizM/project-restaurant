package com.order.serviceorder.dtos.employee;

public class EmployeeRequestDto {

    private String name;

    public EmployeeRequestDto() {
    }

    public EmployeeRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
