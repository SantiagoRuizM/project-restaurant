package com.order.serviceorder.dtos.order;

import com.order.serviceorder.externals.CampusEntity;

public class OrderClaimResponseDto {

    private CampusEntity campus;
    private String deliveryId;

    public OrderClaimResponseDto() {
    }

    public OrderClaimResponseDto(CampusEntity campus, String deliveryId) {
        this.campus = campus;
        this.deliveryId = deliveryId;
    }

    public CampusEntity getCampus() {
        return campus;
    }

    public void setCampus(CampusEntity campus) {
        this.campus = campus;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }
}
