package com.mla.eticket.eventservice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "event_ticket_variant")
@Data
public class EventTicketVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "eventTicketSectionId", referencedColumnName = "id", nullable = false)
    private EventTicketSection eventTicketSection;

    private String name;
    private Integer price;
    private String currency;
    private String displayPrice;
    private String displayCurrency;
    private Integer maxQuantity;
    private Integer total;
    private Integer stock;

}
