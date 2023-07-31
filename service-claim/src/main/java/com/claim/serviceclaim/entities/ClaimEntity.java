package com.claim.serviceclaim.entities;

import jakarta.persistence.*;

@Entity(name = "claim_entity")
public class ClaimEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "delivery_id", nullable = false)
    private String deliveryId;
    @Column(name = "campus", nullable = false)
    private Long campus;
    @Column(name = "reason_claim", nullable = false)
    private String reasonClaim;
    @Column(name = "accepted")
    private boolean accepted = false;
    @Column(name = "action_claim")
    private String actionClaim = null;

    public ClaimEntity() {
    }

    public ClaimEntity(String deliveryId, Long campus, String reasonClaim) {
        this.deliveryId = deliveryId;
        this.campus = campus;
        this.reasonClaim = reasonClaim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getCampus() {
        return campus;
    }

    public void setCampus(Long campus) {
        this.campus = campus;
    }

    public String getReasonClaim() {
        return reasonClaim;
    }

    public void setReasonClaim(String reasonClaim) {
        this.reasonClaim = reasonClaim;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getActionClaim() {
        return actionClaim;
    }

    public void setActionClaim(String actionClaim) {
        this.actionClaim = actionClaim;
    }
}
