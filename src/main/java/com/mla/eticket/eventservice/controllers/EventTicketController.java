package com.mla.eticket.eventservice.controllers;

import com.mla.eticket.commonlib.controllers.CrudController;
import com.mla.eticket.commonlib.response.CommonResponse;
import com.mla.eticket.commonlib.response.CommonResponseError;
import com.mla.eticket.eventservice.dto.request.EventTicketRequest;
import com.mla.eticket.eventservice.entities.Event;
import com.mla.eticket.eventservice.entities.EventTicket;
import com.mla.eticket.eventservice.services.EventService;
import com.mla.eticket.eventservice.services.EventTicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/event-ticket")
public class EventTicketController extends CrudController<EventTicket, Long> {

    private static final Logger log = LogManager.getLogger(EventTicketController.class);

    private final EventTicketService eventTicketService;
    private final EventService eventService;

    @Autowired
    public EventTicketController(EventTicketService eventTicketService, EventService eventService) {
        super("event-ticket");
        this.eventTicketService = eventTicketService;
        this.eventService = eventService;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public List<EventTicket> getAll() {
        return eventTicketService.select();
    }

    @Override
    public Optional<EventTicket> getData(Long id) {
        return eventTicketService.find(id);
    }

    @Override
    public Boolean remove(EventTicket data) {
        return eventTicketService.delete(data);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            Optional<EventTicket> optEventTicket = eventTicketService.find(id);
            if (optEventTicket.isPresent()) {
                return new ResponseEntity<>(new CommonResponse<>("Event Ticket found", optEventTicket.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CommonResponseError("Event Ticket Not Found"), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            log.error("ERROR", ex);
            return new ResponseEntity<>(new CommonResponseError(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody EventTicketRequest request) {
        try {
            Optional<Event> optEvent = eventService.find(request.getEventId());
            EventTicket eventTicket = new EventTicket();
            if (optEvent.isPresent()) {
                eventTicket.setEvent(optEvent.get());
                eventTicket.setDisplayDate(request.getDisplayDate());
                eventTicket.setSaleEndDate(request.getSaleEndDate());
                eventTicketService.save(eventTicket);
            } else {
                throw new Exception("Event not found");
            }
            return new ResponseEntity<>(new CommonResponse<EventTicket>("Event Ticket created successfully"), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("ERROR", ex);
            return new ResponseEntity<>(new CommonResponseError(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
