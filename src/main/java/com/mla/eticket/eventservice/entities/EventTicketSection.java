package com.mla.eticket.eventservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "event_ticket_section")
@Data
public class EventTicketSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "eventTicketId", referencedColumnName = "id", nullable = false)
    private EventTicket eventTicket;

    private String sectionName;
    private String sectionAvatar;
    private String ticketName;
    private String ticketDesc;
    private String isSoldOut;

    @OneToMany(mappedBy = "eventTicketSection")
    private List<EventTicketVariant> eventTicketVariants;

}
