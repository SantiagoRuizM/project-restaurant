package com.order.serviceorder.validations;

import com.order.serviceorder.entities.OrderEntity;
import com.order.serviceorder.entities.OrderStateEntity;
import com.order.serviceorder.exceptions.RecordNotFoundException;

import java.util.List;
import java.util.Optional;

public class OrderStateValidations {

    public static void validateOrderStatesPresent(List<OrderStateEntity> orderStates, Long id) {
        if (orderStates.isEmpty()) throw new RecordNotFoundException("The orders by state with order id " + id + ": was not found");
    }
}
