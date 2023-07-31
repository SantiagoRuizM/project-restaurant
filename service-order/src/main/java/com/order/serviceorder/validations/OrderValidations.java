package com.order.serviceorder.validations;

import com.order.serviceorder.entities.OrderEntity;
import com.order.serviceorder.exceptions.*;

import java.util.Optional;

public class OrderValidations {

    public static void validateDishActive(boolean active, Long id) {
        if (active) throw new InactiveDishException("The dish with id " + id + ": is not active");

    }

    public static void validateDishCampus(Long campusDish, Long campusOrder, Long id) {
        if (campusDish != campusOrder) throw new IncorrectDishCampusException("The dish with id " + id + ": is not valid in your campus");
    }

    public static void validateUserExists(boolean exists, Long id) {
        if (exists) throw new RecordNotFoundException("The user with id " + id + ": was not found");
    }
    public static void validateUserOrderActive(boolean active, Long id) {
        if (active) throw new OrderInProcessException("The user with id " + id + ": already have an order in process");
    }

    public static void validatePage(int page, int size) {
        if (page * 10 > size) throw new InvalidPageException("The page " + (page + 1) + ": does not exist");
    }

    public static void validateOrderPresent(Optional<OrderEntity> order, Long id) {
        if (order.isEmpty()) throw new RecordNotFoundException("The order with id " + id + ": was not found");
    }

    public static void validateOrderPresent(Optional<OrderEntity> order, String deliveryId) {
        if (order.isEmpty()) throw new RecordNotFoundException("The order with delivery id " + deliveryId + ": was not found");
    }

    public static void validateStateFinish(String state, Long id) {
        if (state.equals("Entregado")) throw new StateDeliveryException("The order with id " + id + ": has already been delivered");
    }

    public static void validateStateCancelled(String state, Long id) {
        if (state.equals("Cancelado")) throw new OrderCancelledException("The order with id " + id + ": has been canceled");
    }

    public static void validateStateNotEarring(String state, Long id) {
        if (!state.equals("Pendiente")) throw new OrderInPreparationException("The order with id " + id + ": sorry, you order is already in preparation and cannot be canceled");
    }
}
