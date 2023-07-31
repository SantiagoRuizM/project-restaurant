package com.claim.serviceclaim.dtos.claim;

public class ClaimRequestDto {

    private String deliveryId;
    private String reasonClaim;

    public ClaimRequestDto() {
    }

    public ClaimRequestDto(String deliveryId, String reasonClaim) {
        this.deliveryId = deliveryId;
        this.reasonClaim = reasonClaim;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getReasonClaim() {
        return reasonClaim;
    }

    public void setReasonClaim(String reasonClaim) {
        this.reasonClaim = reasonClaim;
    }
}
