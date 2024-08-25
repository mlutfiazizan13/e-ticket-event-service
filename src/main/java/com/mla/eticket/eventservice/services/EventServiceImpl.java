package com.mla.eticket.eventservice.services;

import com.mla.eticket.eventservice.entities.Event;
import com.mla.eticket.eventservice.repositories.EventRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class EventServiceImpl implements EventService{

    private static final Logger log = LogManager.getLogger(EventServiceImpl.class);

    private final EventRepository repository;

    @Autowired
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event save(Event event) {
        return repository.save(event);
    }
}
