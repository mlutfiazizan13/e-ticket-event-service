package com.mla.eticket.eventservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mla.eticket.commonlib.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "event_ticket_sections")
@Data
@Setter
@Getter
public class EventTicketSection extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"eventTicketSections", "hibernateLazyInitializer"})
    @ManyToOne
    @JoinColumn(name = "eventTicketId", referencedColumnName = "id", nullable = false)
    private EventTicket eventTicket;

    private String sectionName;
    private String sectionAvatar;
    private String ticketName;
    private String ticketDesc;
    private Boolean isSoldOut;

    @OneToMany(mappedBy = "eventTicketSection")
    private List<EventTicketVariant> eventTicketVariants;

}
