package com.mla.eticket.eventservice.services;

import com.mla.eticket.eventservice.entities.EventTicket;
import com.mla.eticket.eventservice.entities.EventTicketSection;
import com.mla.eticket.eventservice.repositories.EventTicketRepository;
import com.mla.eticket.eventservice.repositories.EventTicketSectionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventTicketSectionServiceImpl implements EventTicketSectionService{

    private static final Logger log = LogManager.getLogger(EventTicketSectionServiceImpl.class);

    private final EventTicketSectionRepository repository;

    @Autowired
    public EventTicketSectionServiceImpl(EventTicketSectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventTicketSection> select() {
        return (List<EventTicketSection>) repository.findAll();
    }

    @Override
    public Optional<EventTicketSection> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public Boolean delete(EventTicketSection eventTicketSection) {
        try {
            repository.delete(eventTicketSection);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public EventTicketSection save(EventTicketSection eventTicketSection) {
        return repository.save(eventTicketSection);
    }
}
