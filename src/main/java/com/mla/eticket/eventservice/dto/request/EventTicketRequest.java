package com.mla.eticket.eventservice.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventTicketRequest {
    private Long eventId;
    private String displayDate;
    private LocalDateTime saleEndDate;
}
