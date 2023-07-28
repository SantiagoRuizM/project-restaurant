package com.dish.servicedish.validations;

import com.dish.servicedish.entity.DishEntity;
import com.dish.servicedish.exceptions.InvalidPageException;
import com.dish.servicedish.exceptions.PriceNegativeException;
import com.dish.servicedish.exceptions.RecordNotFoundException;

import java.util.Optional;

public class DishValidations {

    public static void validatePriceNegative(Long price) {
        if (price <= 0) throw new PriceNegativeException("The price must be positive");
    }

    public static void validatePage(int page, int size) {
        if (page * 10 > size) throw new InvalidPageException("The page " + (page + 1) + ": does not exist");
    }

    public static void validateDishPresent(Optional<DishEntity> dish, Long id) {
        if (dish.isEmpty()) throw new RecordNotFoundException("The dish with id " + id + ": was not found");
    }
}
