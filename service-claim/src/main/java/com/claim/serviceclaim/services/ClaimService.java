package com.claim.serviceclaim.services;

import com.claim.serviceclaim.dtos.claim.ClaimRequestDto;
import com.claim.serviceclaim.entities.ClaimEntity;
import com.claim.serviceclaim.exceptions.DishFailedResponseControllerException;
import com.claim.serviceclaim.externals.OrderClaimResponseDto;
import com.claim.serviceclaim.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private RestTemplate restTemplate;

    public void createClaim(ClaimRequestDto claimRequestDto) {
        try {
            OrderClaimResponseDto responseDto = restTemplate.getForObject("http://localhost:8082/serviceOrders/orders/get/delivery?deliveryId=" + claimRequestDto.getDeliveryId(), OrderClaimResponseDto.class);
            claimRepository.save(new ClaimEntity(claimRequestDto.getDeliveryId(), responseDto.getCampus().getId(), claimRequestDto.getReasonClaim()));
        } catch (Exception e) {
            throw new DishFailedResponseControllerException(e.getMessage());
        }
    }
}
