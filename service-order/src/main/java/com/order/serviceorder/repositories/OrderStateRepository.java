package com.order.serviceorder.repositories;

import com.order.serviceorder.entities.OrderStateEntity;
import com.order.serviceorder.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderStateRepository extends JpaRepository<OrderStateEntity, Long> {

    List<OrderStateEntity> findByNumberOrderOrderById(Long numberOrder);

    OrderStateEntity findByNumberOrderAndState(Long numberOrder, StateEnum state);
}
