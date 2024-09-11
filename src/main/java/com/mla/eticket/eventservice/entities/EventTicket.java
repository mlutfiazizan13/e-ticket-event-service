package com.mla.eticket.eventservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mla.eticket.commonlib.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "event_tickets")
@Data
@Setter
@Getter
public class EventTicket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"eventTickets", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eventId", referencedColumnName = "id", nullable = false)
    private Event event;
    private String displayDate;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime saleEndDate;
    @OneToMany(mappedBy = "eventTicket")

    private List<EventTicketSection> eventTicketSections;
}
