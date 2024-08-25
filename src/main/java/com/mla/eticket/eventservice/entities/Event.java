package com.mla.eticket.eventservice.entities;

import com.mla.eticket.commonlib.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "events")
@Data
public class Event extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String address;
    @Column(columnDefinition = "TEXT")
    private String googleMapsEmbed;
    @Column(columnDefinition = "TEXT")
    private String eventDetails;
    @OneToMany(mappedBy = "event")
    private List<EventTicket> eventTickets;

}
