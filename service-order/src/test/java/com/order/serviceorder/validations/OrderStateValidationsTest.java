package com.order.serviceorder.validations;

import com.order.serviceorder.entities.OrderStateEntity;
import com.order.serviceorder.exceptions.RecordNotFoundException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderStateValidationsTest {

    @Test
    public void validateOrderStatesPresent_where_order_states_has_values() {
        OrderStateValidations.validateOrderStatesPresent(List.of(new OrderStateEntity()), 0L);
    }

    @Test(expected = RecordNotFoundException.class)
    public void validateOrderStatesPresent_when_order_states_is_empty() {
        OrderStateValidations.validateOrderStatesPresent(new ArrayList<>(), 0L);
    }
}
