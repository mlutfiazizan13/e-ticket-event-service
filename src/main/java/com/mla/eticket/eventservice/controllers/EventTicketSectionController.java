package com.mla.eticket.eventservice.controllers;

import com.mla.eticket.commonlib.controllers.CrudController;
import com.mla.eticket.commonlib.response.CommonResponse;
import com.mla.eticket.commonlib.response.CommonResponseError;
import com.mla.eticket.eventservice.dto.request.EventTicketRequest;
import com.mla.eticket.eventservice.dto.request.EventTicketSectionRequest;
import com.mla.eticket.eventservice.entities.Event;
import com.mla.eticket.eventservice.entities.EventTicket;
import com.mla.eticket.eventservice.entities.EventTicketSection;
import com.mla.eticket.eventservice.services.EventService;
import com.mla.eticket.eventservice.services.EventTicketSectionService;
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
@RequestMapping(value = "/event-ticket-section")
public class EventTicketSectionController extends CrudController<EventTicketSection, Long> {

    private static final Logger log = LogManager.getLogger(EventTicketSectionController.class);

    private final EventTicketSectionService eventTicketSectionService;
    private final EventTicketService eventTicketService;

    @Autowired
    public EventTicketSectionController(EventTicketSectionService eventTicketSectionService, EventTicketService eventTicketService) {
        super("event-ticket-section");

        this.eventTicketSectionService = eventTicketSectionService;
        this.eventTicketService = eventTicketService;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public List<EventTicketSection> getAll() {
        return eventTicketSectionService.select();
    }

    @Override
    public Optional<EventTicketSection> getData(Long id) {
        return eventTicketSectionService.find(id);
    }

    @Override
    public Boolean remove(EventTicketSection data) {
        return eventTicketSectionService.delete(data);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            Optional<EventTicketSection> optionalEventTicketSection = eventTicketSectionService.find(id);
            if (optionalEventTicketSection.isPresent()) {
                return new ResponseEntity<>(new CommonResponse<>("Event Ticket Section found", optionalEventTicketSection.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CommonResponseError("Event Ticket Section Not Found"), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            log.error("ERROR", ex);
            return new ResponseEntity<>(new CommonResponseError(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody EventTicketSectionRequest request) {
        try {
            Optional<EventTicket> optionalEventTicket = eventTicketService.find(request.getEventTicketId());
            EventTicketSection eventTicketSection = new EventTicketSection();
            if (optionalEventTicket.isPresent()) {
                eventTicketSection.setEventTicket(optionalEventTicket.get());
                eventTicketSection.setSectionName(request.getSectionName());
                eventTicketSection.setSectionAvatar(request.getSectionAvatar());
                eventTicketSection.setTicketName(request.getTicketName());
                eventTicketSection.setTicketDesc(request.getTicketDesc());
                eventTicketSection.setIsSoldOut(request.getIsSoldOut());
                eventTicketSectionService.save(eventTicketSection);
            } else {
                throw new Exception("Event Ticket not found");
            }
            return new ResponseEntity<>(new CommonResponse<>("Event Ticket Section created successfully"), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("ERROR", ex);
            return new ResponseEntity<>(new CommonResponseError(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
