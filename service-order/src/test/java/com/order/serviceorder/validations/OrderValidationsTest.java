package com.order.serviceorder.validations;

import com.order.serviceorder.entities.OrderEntity;
import com.order.serviceorder.enums.StateEnum;
import com.order.serviceorder.exceptions.*;
import java.util.Optional;

public class OrderValidationsTest {

    @org.junit.Test
    public void validateFactRequired_when_data_is_not_null() {
        OrderValidations.validateFactRequired("", "");
    }

    @org.junit.Test(expected = FactRequiredException.class)
    public void validateFactRequired_when_data_is_null() {
        OrderValidations.validateFactRequired(null, "");
    }

    @org.junit.Test
    public void validateDishActive_when_active_is_true() {
        OrderValidations.validateDishActive(true, 0L);
    }

    @org.junit.Test(expected = InactiveDishException.class)
    public void validateDishActive_when_active_is_false() {
        OrderValidations.validateDishActive(false, 0L);
    }

    @org.junit.Test
    public void validateDishCampus_when_campusDish_equal_campusOrder() {
        OrderValidations.validateDishCampus(11L, 11L, 0L);
    }

    @org.junit.Test(expected = IncorrectDishCampusException.class)
    public void validateDishCampus_when_campusDish_not_equal_campusOrder() {
        OrderValidations.validateDishCampus(5L, 11L, 0L);
    }

    @org.junit.Test
    public void validateUserExists_when_exists_is_false() {
        OrderValidations.validateUserExists(false, 0L);
    }

    @org.junit.Test(expected = RecordNotFoundException.class)
    public void validateUserExists_when_exists_is_true() {
        OrderValidations.validateUserExists(true, 0L);
    }

    @org.junit.Test
    public void validateEmployeeExists_when_exists_is_true() {
        OrderValidations.validateEmployeeExists(true, 0L);
    }

    @org.junit.Test(expected = RecordNotFoundException.class)
    public void validateEmployeeExists_when_exists_is_false() {
        OrderValidations.validateEmployeeExists(false, 0L);
    }

    @org.junit.Test
    public void validateUserOrderActive_when_active_is_false() {
        OrderValidations.validateUserOrderActive(false, 0L);
    }

    @org.junit.Test(expected = OrderInProcessException.class)
    public void validateUserOrderActive_when_active_is_true() {
        OrderValidations.validateUserOrderActive(true, 0L);
    }

    @org.junit.Test
    public void validatePage_when_page_x_10_is_minor_to_size() {
        OrderValidations.validatePage(1, 11);
    }

    @org.junit.Test(expected = InvalidPageException.class)
    public void validatePage_when_page_x_10_is_equal_to_size() {
        OrderValidations.validatePage(1, 10);
    }

    @org.junit.Test(expected = InvalidPageException.class)
    public void validatePage_when_page_x_10_is_elderly_to_size() {
        OrderValidations.validatePage(1, 9);
    }

    @org.junit.Test
    public void validateOrderPresent_when_order_for_id_is_present() {
        OrderValidations.validateOrderPresent(Optional.of(new OrderEntity()), 0L);
    }

    @org.junit.Test(expected = RecordNotFoundException.class)
    public void validateOrderPresent_when_order_for_id_is_not_present() {
        OrderValidations.validateOrderPresent(Optional.empty(), 0L);
    }

    @org.junit.Test
    public void validateOrderPresent_when_order_for_deliveryId_is_present() {
        OrderValidations.validateOrderPresent(Optional.of(new OrderEntity()), "");
    }

    @org.junit.Test(expected = RecordNotFoundException.class)
    public void validateOrderPresent_when_order_for_deliveryId_is_not_present() {
        OrderValidations.validateOrderPresent(Optional.empty(), "");
    }

    @org.junit.Test
    public void validateStateEarring_when_state_is_not_earring() {
        OrderValidations.validateStateEarring(StateEnum.READY, 0L);
    }

    @org.junit.Test(expected = OrderEarringException.class)
    public void validateStateEarring_when_state_is_earring() {
        OrderValidations.validateStateEarring(StateEnum.EARRING, 0L);
    }

    @org.junit.Test
    public void validateStateDelivered_when_state_is_not_delivered() {
        OrderValidations.validateStateDelivered(StateEnum.CANCELLED, 0L);
    }

    @org.junit.Test(expected = OrderDeliveryException.class)
    public void validateStateDelivered_when_state_is_delivered() {
        OrderValidations.validateStateDelivered(StateEnum.DELIVERED, 0L);
    }

    @org.junit.Test
    public void validateStateCancelled_when_state_is_not_cancelled() {
        OrderValidations.validateStateCancelled(StateEnum.READY, 0L);
    }

    @org.junit.Test(expected = OrderCancelledException.class)
    public void validateStateCancelled_when_state_is_cancelled() {
        OrderValidations.validateStateCancelled(StateEnum.CANCELLED, 0L);
    }

    @org.junit.Test
    public void validateStateNotEarringForCancelled_when_state_is_not_earring() {
        OrderValidations.validateStateNotEarringForCancelled(StateEnum.EARRING, 0L);
    }

    @org.junit.Test(expected = OrderInPreparationException.class)
    public void validateStateNotEarringForCancelled_when_state_is_earring() {
        OrderValidations.validateStateNotEarringForCancelled(StateEnum.IN_PREPARATION, 0L);
    }

    @org.junit.Test
    public void validateStateNotEarringForAssignEmployee_when_state_is_not_earring() {
        OrderValidations.validateStateNotEarringForAssignEmployee(StateEnum.EARRING, 0L);
    }

    @org.junit.Test(expected = OrderInPreparationException.class)
    public void validateStateNotEarringForAssignEmployee_when_state_is_earring() {
        OrderValidations.validateStateNotEarringForAssignEmployee(StateEnum.READY, 0L);
    }
}
