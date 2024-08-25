package com.mla.eticket.eventservice.entities;

import com.mla.eticket.commonlib.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event_tickets")
@Data
public class EventTicket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "id", nullable = false)
    private Event event;
    private String displayDate;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime aleEndDate;

    @OneToMany(mappedBy = "eventTicket")
    private List<EventTicketSection> eventTicketSections;
}
