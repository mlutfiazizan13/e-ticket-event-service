package com.mla.eticket.eventservice.services;

import com.mla.eticket.eventservice.entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    List<Event> select();

    Optional<Event> find(Long id);

    Boolean delete(Event event);

    Event save(Event event);

    void sendEventMessage(Event event);
}

