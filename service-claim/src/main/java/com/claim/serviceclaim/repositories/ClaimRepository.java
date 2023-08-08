package com.claim.serviceclaim.repositories;

import com.claim.serviceclaim.entities.ClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<ClaimEntity, Long> {

    List<ClaimEntity> findAllByOrderById();
}
