package com.claim.serviceclaim.services;

import com.claim.serviceclaim.dtos.claim.ClaimActionRequestDto;
import com.claim.serviceclaim.dtos.claim.ClaimRequestDto;
import com.claim.serviceclaim.entities.ClaimEntity;
import com.claim.serviceclaim.enums.ActionEnum;
import com.claim.serviceclaim.exceptions.DishFailedResponseControllerException;
import com.claim.serviceclaim.externals.OrderClaimResponseDto;
import com.claim.serviceclaim.repositories.ClaimRepository;
import com.claim.serviceclaim.validations.ClaimValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class ClaimService extends ClaimValidations {

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private RestTemplate restTemplate;

    public void createClaim(ClaimRequestDto claimRequestDto) {
        validateFactRequired(claimRequestDto.getDeliveryId(), "delivery id");
        validateFactRequired(claimRequestDto.getReasonClaim(), "reason claim");
        try {
            OrderClaimResponseDto responseDto = restTemplate.getForObject("http://SERVICE-ORDER/serviceOrders/orders/get/delivery?deliveryId=" + claimRequestDto.getDeliveryId(), OrderClaimResponseDto.class);
            claimRepository.save(new ClaimEntity(claimRequestDto.getDeliveryId(), responseDto.getCampus().getId(), claimRequestDto.getReasonClaim()));
        } catch (Exception e) {
            throw new DishFailedResponseControllerException(e.getMessage());
        }
    }

    public List<ClaimEntity> getAllClaims() {
        return claimRepository.findAllByOrderById();
    }

    public void updateClaim(ClaimActionRequestDto claimActionRequestDto, ActionEnum accepted) {
        validateFactRequired(claimActionRequestDto.getClaimId(), "claim id");
        validateFactRequired(claimActionRequestDto.getActionClaim(), "action claim");
        Optional<ClaimEntity> claimEntity = claimRepository.findById(claimActionRequestDto.getClaimId());
        validateClaimPresent(claimEntity, claimActionRequestDto.getClaimId());
        ClaimEntity data = claimEntity.get();
        data.setAccepted(accepted);
        data.setActionClaim(claimActionRequestDto.getActionClaim());
        claimRepository.save(data);
    }

    public void updateClaimAccepted(ClaimActionRequestDto claimActionRequestDto) {
        updateClaim(claimActionRequestDto, ActionEnum.ACCEPTED);
    }

    public void updateClaimRejected(ClaimActionRequestDto claimActionRequestDto) {
        updateClaim(claimActionRequestDto, ActionEnum.REJECTED);
    }
}
