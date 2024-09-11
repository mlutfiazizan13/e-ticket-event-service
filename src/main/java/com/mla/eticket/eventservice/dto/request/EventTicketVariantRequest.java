package com.mla.eticket.eventservice.dto.request;

import lombok.Data;

@Data
public class EventTicketVariantRequest {
    private Long eventTicketSectionId;
    private String name;
    private Integer price;
    private String currency;
    private String displayPrice;
    private String displayCurrency;
    private Integer maxQuantity;
    private Integer total;
    private Integer stock;
}
