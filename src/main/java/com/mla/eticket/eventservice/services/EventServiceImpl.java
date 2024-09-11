package com.mla.eticket.eventservice.services;

import com.mla.eticket.eventservice.entities.Event;
import com.mla.eticket.eventservice.repositories.EventRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    private static final Logger log = LogManager.getLogger(EventServiceImpl.class);

    private final EventRepository repository;
    private final KafkaTemplate<String, Event> eventKafkaTemplate;

    @Autowired
    public EventServiceImpl(EventRepository repository, KafkaTemplate<String, Event> eventKafkaTemplate) {
        this.repository = repository;
        this.eventKafkaTemplate = eventKafkaTemplate;
    }

    @Override
    public List<Event> select() {
        return (List<Event>) repository.findAll();
    }

    @Override
    public Optional<Event> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public Boolean delete(Event event) {
        try {
            repository.delete(event);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public Event save(Event event) {
        return repository.save(event);
    }

    @Override
    public void sendEventMessage(Event event) {
        eventKafkaTemplate.send("events", event);
    }
}
