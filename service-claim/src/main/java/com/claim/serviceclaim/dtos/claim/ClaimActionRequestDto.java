package com.claim.serviceclaim.dtos.claim;

public class ClaimActionRequestDto {

    private Long claimId;
    private String actionClaim;

    public ClaimActionRequestDto() {
    }

    public ClaimActionRequestDto(Long claimId, String actionClaim) {
        this.claimId = claimId;
        this.actionClaim = actionClaim;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getActionClaim() {
        return actionClaim;
    }

    public void setActionClaim(String actionClaim) {
        this.actionClaim = actionClaim;
    }
}
