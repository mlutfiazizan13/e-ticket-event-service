package com.mla.eticket.eventservice.controllers;

import com.mla.eticket.commonlib.controllers.CrudController;
import com.mla.eticket.commonlib.response.CommonResponse;
import com.mla.eticket.commonlib.response.CommonResponseError;
import com.mla.eticket.eventservice.dto.request.EventTicketSectionRequest;
import com.mla.eticket.eventservice.dto.request.EventTicketVariantRequest;
import com.mla.eticket.eventservice.entities.EventTicket;
import com.mla.eticket.eventservice.entities.EventTicketSection;
import com.mla.eticket.eventservice.entities.EventTicketVariant;
import com.mla.eticket.eventservice.services.EventTicketSectionService;
import com.mla.eticket.eventservice.services.EventTicketService;
import com.mla.eticket.eventservice.services.EventTicketVariantService;
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
@RequestMapping(value = "/event-ticket-variant")
public class EventTicketVariantController extends CrudController<EventTicketVariant, Long> {

    private static final Logger log = LogManager.getLogger(EventTicketVariantController.class);

    private final EventTicketVariantService eventTicketVariantService;
    private final EventTicketSectionService eventTicketSectionService;

    @Autowired
    public EventTicketVariantController(EventTicketVariantService eventTicketVariantService, EventTicketSectionService eventTicketSectionService) {
        super("event-ticket-variant");
        this.eventTicketVariantService = eventTicketVariantService;
        this.eventTicketSectionService = eventTicketSectionService;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public List<EventTicketVariant> getAll() {
        return eventTicketVariantService.select();
    }

    @Override
    public Optional<EventTicketVariant> getData(Long id) {
        return eventTicketVariantService.find(id);
    }

    @Override
    public Boolean remove(EventTicketVariant data) {
        return eventTicketVariantService.delete(data);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            Optional<EventTicketVariant> optionalEventTicketSection = eventTicketVariantService.find(id);
            if (optionalEventTicketSection.isPresent()) {
                return new ResponseEntity<>(new CommonResponse<>("Event Ticket Variant found", optionalEventTicketSection.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CommonResponseError("Event Ticket Variant Not Found"), HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            log.error("ERROR", ex);
            return new ResponseEntity<>(new CommonResponseError(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody EventTicketVariantRequest request) {
        try {
            Optional<EventTicketSection> optionalEventTicketSection = eventTicketSectionService.find(request.getEventTicketSectionId());
            EventTicketVariant eventTicketVariant = new EventTicketVariant();
            if (optionalEventTicketSection.isPresent()) {
                eventTicketVariant.setName(request.getName());
                eventTicketVariant.setPrice(request.getPrice());
                eventTicketVariant.setCurrency(request.getCurrency());
                eventTicketVariant.setDisplayPrice(request.getDisplayPrice());
                eventTicketVariant.setDisplayCurrency(request.getDisplayCurrency());
                eventTicketVariant.setMaxQuantity(request.getMaxQuantity());
                eventTicketVariant.setTotal(request.getTotal());
                eventTicketVariant.setStock(request.getStock());
                eventTicketVariantService.save(eventTicketVariant);
            } else {
                throw new Exception("Event Ticket Section not found");
            }
            return new ResponseEntity<>(new CommonResponse<>("Event Ticket Variant created successfully"), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("ERROR", ex);
            return new ResponseEntity<>(new CommonResponseError(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
