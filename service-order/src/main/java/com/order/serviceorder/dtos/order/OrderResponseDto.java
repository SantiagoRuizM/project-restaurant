package com.order.serviceorder.dtos.order;

import com.order.serviceorder.dtos.dish.DishResponseDto;
import com.order.serviceorder.dtos.employee.EmployeeRequestDto;
import com.order.serviceorder.dtos.user.UserRequestDto;
import com.order.serviceorder.enums.StateEnum;
import com.order.serviceorder.externals.CampusEntity;
import java.util.List;

public class OrderResponseDto {

    private Long id;
    private List<DishResponseDto> dishes;
    private CampusEntity campus;
    private StateEnum state;
    private UserRequestDto user;
    private EmployeeRequestDto employee;
    private String deliveryId;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Long id, StateEnum state, UserRequestDto user, EmployeeRequestDto employee, String deliveryId) {
        this.id = id;
        this.state = state;
        this.user = user;
        this.employee = employee;
        this.deliveryId = deliveryId;
    }

    public OrderResponseDto(CampusEntity campus, String deliveryId) {
        this.campus = campus;
        this.deliveryId = deliveryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DishResponseDto> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishResponseDto> dishes) {
        this.dishes = dishes;
    }

    public CampusEntity getCampus() {
        return campus;
    }

    public void setCampus(CampusEntity campus) {
        this.campus = campus;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public UserRequestDto getUser() {
        return user;
    }

    public void setUser(UserRequestDto user) {
        this.user = user;
    }

    public EmployeeRequestDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeRequestDto employee) {
        this.employee = employee;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }
}
