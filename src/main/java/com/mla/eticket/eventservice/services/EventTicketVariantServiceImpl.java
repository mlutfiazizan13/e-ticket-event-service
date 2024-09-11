package com.mla.eticket.eventservice.services;

import com.mla.eticket.eventservice.entities.EventTicket;
import com.mla.eticket.eventservice.entities.EventTicketVariant;
import com.mla.eticket.eventservice.repositories.EventTicketRepository;
import com.mla.eticket.eventservice.repositories.EventTicketVariantRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventTicketVariantServiceImpl implements EventTicketVariantService{

    private static final Logger log = LogManager.getLogger(EventTicketVariantServiceImpl.class);

    private final EventTicketVariantRepository repository;

    @Autowired
    public EventTicketVariantServiceImpl(EventTicketVariantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EventTicketVariant> select() {
        return (List<EventTicketVariant>) repository.findAll();
    }

    @Override
    public Optional<EventTicketVariant> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public Boolean delete(EventTicketVariant eventTicketVariant) {
        try {
            repository.delete(eventTicketVariant);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public EventTicketVariant save(EventTicketVariant eventTicketVariant) {
        return repository.save(eventTicketVariant);
    }
}
