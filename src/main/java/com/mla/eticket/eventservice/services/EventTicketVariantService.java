package com.mla.eticket.eventservice.services;

import com.mla.eticket.eventservice.entities.EventTicket;
import com.mla.eticket.eventservice.entities.EventTicketVariant;

import java.util.List;
import java.util.Optional;

public interface EventTicketVariantService {

    List<EventTicketVariant> select();

    Optional<EventTicketVariant> find(Long id);

    Boolean delete(EventTicketVariant event);

    EventTicketVariant save(EventTicketVariant event);
}

