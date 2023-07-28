package com.order.serviceorder.validations;

import com.order.serviceorder.exceptions.*;

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
}
