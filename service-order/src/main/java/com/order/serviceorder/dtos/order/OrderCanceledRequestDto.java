package com.order.serviceorder.dtos.order;

public class OrderCanceledRequestDto {

    private Long numberOrder;
    private String reasonCancellation;

    public OrderCanceledRequestDto() {
    }

    public OrderCanceledRequestDto(Long numberOrder, String reasonCancellation) {
        this.numberOrder = numberOrder;
        this.reasonCancellation = reasonCancellation;
    }

    public Long getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(Long numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getReasonCancellation() {
        return reasonCancellation;
    }

    public void setReasonCancellation(String reasonCancellation) {
        this.reasonCancellation = reasonCancellation;
    }
}
