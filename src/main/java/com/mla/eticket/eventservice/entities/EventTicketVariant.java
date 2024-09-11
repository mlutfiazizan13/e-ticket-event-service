package com.mla.eticket.eventservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mla.eticket.commonlib.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "event_ticket_variants")
@Data
public class EventTicketVariant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"eventTicketVariants", "hibernateLazyInitializer"})
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
