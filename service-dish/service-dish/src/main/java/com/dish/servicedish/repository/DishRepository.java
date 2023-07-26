package com.dish.servicedish.repository;

import com.dish.servicedish.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Long> {

    List<DishEntity> findByCategoryIdOrCampusId(Long category, Long campus);

    List<DishEntity> findByActive(boolean active);
}
