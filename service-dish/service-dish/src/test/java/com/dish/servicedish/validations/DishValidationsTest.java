package com.dish.servicedish.validations;

import com.dish.servicedish.entity.DishEntity;
import com.dish.servicedish.exceptions.FactRequiredException;
import com.dish.servicedish.exceptions.InvalidPageException;
import com.dish.servicedish.exceptions.PriceNegativeException;
import com.dish.servicedish.exceptions.RecordNotFoundException;
import org.junit.Assert;

import java.util.Optional;

public class DishValidationsTest {

    @org.junit.Test
    public void validateFactRequired_when_data_is_not_null() {
        DishValidations.validateFactRequired("", "");
    }

    @org.junit.Test(expected = FactRequiredException.class)
    public void validateFactRequired_when_data_is_null() {
        DishValidations.validateFactRequired(null, "");
    }

    @org.junit.Test
    public void validatePriceNegative_when_price_is_elderly_to_zero() {
        DishValidations.validatePriceNegative(1L);
    }

    @org.junit.Test(expected = PriceNegativeException.class)
    public void validatePriceNegative_when_price_is_equal_to_zero() {
        DishValidations.validatePriceNegative(0L);
    }

    @org.junit.Test(expected = PriceNegativeException.class)
    public void validatePriceNegative_when_price_is_minor_to_zero() {
        DishValidations.validatePriceNegative(-1L);
    }

    @org.junit.Test
    public void validatePage_when_page_x_10_is_minor_to_size() {
        DishValidations.validatePage(1, 11);
    }

    @org.junit.Test(expected = InvalidPageException.class)
    public void validatePage_when_page_x_10_is_equal_to_size() {
        DishValidations.validatePage(1, 10);
    }

    @org.junit.Test(expected = InvalidPageException.class)
    public void validatePage_when_page_x_10_is_elderly_to_size() {
        DishValidations.validatePage(1, 9);
    }

    @org.junit.Test
    public void validateOrderPresent_when_order_for_id_is_present() {
        DishValidations.validateDishPresent(Optional.of(new DishEntity()), 0L);
    }

    @org.junit.Test(expected = RecordNotFoundException.class)
    public void validateOrderPresent_when_order_for_id_is_not_present() {
        DishValidations.validateDishPresent(Optional.empty(), 0L);
    }

    @org.junit.Test
    public void validateFilterByCampusCategory_when_campus_is_null_and_category_is_null() {
        Assert.assertEquals(1, DishValidations.validateFilterByCampusCategory(null, null));
    }

    @org.junit.Test
    public void validateFilterByCampusCategory_when_campus_is_not_null_and_category_is_not_null() {
        Assert.assertEquals(0, DishValidations.validateFilterByCampusCategory(0L, 0L));
    }

    @org.junit.Test
    public void validateFilterByCampusCategory_when_campus_is_null_and_category_is_not_null() {
        Assert.assertEquals(2, DishValidations.validateFilterByCampusCategory(null, 0L));
    }

    @org.junit.Test
    public void validateFilterByCampusCategory_when_campus_is_not_null_and_category_is_null() {
        Assert.assertEquals(2, DishValidations.validateFilterByCampusCategory(0L, null));
    }
}