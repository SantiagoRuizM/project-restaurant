package com.order.serviceorder.repositories;

import com.order.serviceorder.entities.OrderDishDetailsEntity;
import com.order.serviceorder.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDishDetailsRepository extends JpaRepository<OrderDishDetailsEntity, Long> {

    List<OrderDishDetailsEntity> findByOrderDish(OrderEntity orderDish);
}
