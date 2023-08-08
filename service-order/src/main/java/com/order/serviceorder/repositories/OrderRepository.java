package com.order.serviceorder.repositories;

import com.order.serviceorder.entities.EmployeeEntity;
import com.order.serviceorder.entities.OrderEntity;
import com.order.serviceorder.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByOrderById();

    List<OrderEntity> findByStateAndCampus(StateEnum state, Long campus);

    List<OrderEntity> findByState(StateEnum state);

    List<OrderEntity> findByEndOrderIsNotNull();

    List<OrderEntity> findByEndOrderIsNotNullAndEmployeeOrder(EmployeeEntity employeeOrder);

    Optional<OrderEntity> findByDeliveryId(String deliveryId);
}
