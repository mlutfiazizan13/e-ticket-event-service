package com.mla.eticket.eventservice.services;

import com.mla.eticket.eventservice.entities.Event;
import com.mla.eticket.eventservice.entities.EventTicket;
import com.mla.eticket.eventservice.repositories.EventRepository;
import com.mla.eticket.eventservice.repositories.EventTicketRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventTicketServiceImpl implements EventTicketService{

    private static final Logger log = LogManager.getLogger(EventTicketServiceImpl.class);

    private final EventTicketRepository repository;

    @Autowired
    public EventTicketServiceImpl(EventTicketRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventTicket> select() {
        return (List<EventTicket>) repository.findAll();
    }

    @Override
    public Optional<EventTicket> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public Boolean delete(EventTicket eventTicket) {
        try {
            repository.delete(eventTicket);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public EventTicket save(EventTicket eventTicket) {
        return repository.save(eventTicket);
    }
}
