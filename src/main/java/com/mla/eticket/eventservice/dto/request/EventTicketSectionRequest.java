package com.mla.eticket.eventservice.dto.request;

import lombok.Data;

@Data
public class EventTicketSectionRequest {
    private Long eventTicketId;
    private String sectionName;
    private String sectionAvatar;
    private String ticketName;
    private String ticketDesc;
    private Boolean isSoldOut;
}
