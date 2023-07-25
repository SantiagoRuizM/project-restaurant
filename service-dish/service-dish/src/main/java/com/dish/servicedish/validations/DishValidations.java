package com.dish.servicedish.validations;

public class DishValidations {

    public static boolean validateRole(char role) {
        if (role != '1') return true;
        else return false;
    }

    public static boolean validatePriceNegative(Long price) {
        return price <= 0;
    }
}
