package com.mla.eticket.eventservice.services;

import com.mla.eticket.eventservice.entities.EventTicket;

import java.util.List;
import java.util.Optional;

public interface EventTicketService {

    List<EventTicket> select();

    Optional<EventTicket> find(Long id);

    Boolean delete(EventTicket event);

    EventTicket save(EventTicket event);
}

