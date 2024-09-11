package com.mla.eticket.eventservice.services;

import com.mla.eticket.eventservice.entities.EventTicketSection;

import java.util.List;
import java.util.Optional;

public interface EventTicketSectionService {

    List<EventTicketSection> select();

    Optional<EventTicketSection> find(Long id);

    Boolean delete(EventTicketSection event);

    EventTicketSection save(EventTicketSection event);
}

