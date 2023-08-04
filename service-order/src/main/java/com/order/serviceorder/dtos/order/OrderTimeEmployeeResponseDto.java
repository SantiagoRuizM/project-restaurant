package com.order.serviceorder.dtos.order;

import com.order.serviceorder.dtos.employee.EmployeeResponseDto;
public class OrderTimeEmployeeResponseDto {

    private String timeOrder;
    private String employee;
    private Integer quantityOrders;

    public OrderTimeEmployeeResponseDto() {
    }

    public OrderTimeEmployeeResponseDto(String employee) {
        this.employee = employee;
    }

    public String getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(String timeOrder) {
        this.timeOrder = timeOrder;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Integer getQuantityOrders() {
        return quantityOrders;
    }

    public void setQuantityOrders(Integer quantityOrders) {
        this.quantityOrders = quantityOrders;
    }
}
