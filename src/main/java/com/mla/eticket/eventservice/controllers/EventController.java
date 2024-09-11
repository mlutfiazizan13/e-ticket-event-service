package com.mla.eticket.eventservice.controllers;

import com.mla.eticket.commonlib.controllers.CrudController;
import com.mla.eticket.commonlib.response.CommonResponse;
import com.mla.eticket.commonlib.response.CommonResponseError;
import com.mla.eticket.commonlib.response.CommonStatus;
import com.mla.eticket.eventservice.entities.Event;
import com.mla.eticket.eventservice.services.EventService;
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
@RequestMapping(value = "/event")
public class EventController extends CrudController<Event, Long> {

    private static final Logger log = LogManager.getLogger(EventController.class);

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        super("event");
        this.eventService = eventService;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public List<Event> getAll() {
        return eventService.select();
    }

    @Override
    public Optional<Event> getData(Long id) {
        return eventService.find(id);
    }

    @Override
    public Boolean remove(Event data) {
        return eventService.delete(data);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonStatus> getAllData() {
        try {
            List<Event> events = eventService.select();
            return new ResponseEntity<>(new CommonResponse<>("List Event", events), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("ERROR", ex);
            return new ResponseEntity<>(new CommonResponseError(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonStatus> getOne(@PathVariable Long id) {
        try {
            Optional<Event> optEvent = eventService.find(id);
            return optEvent.<ResponseEntity<CommonStatus>>map(event ->
                    new ResponseEntity<>(new CommonResponse<>("Event found", event), HttpStatus.OK))
                        .orElseGet(() ->
                            new ResponseEntity<>(new CommonResponseError("Event Not Found"), HttpStatus.BAD_REQUEST));

        } catch (Exception ex) {
            log.error("ERROR", ex);
            return new ResponseEntity<>(new CommonResponseError(ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Event data) {
        try {
            Event event = eventService.save(data);
            eventService.sendEventMessage(event);
            return new ResponseEntity<>(new CommonResponse<>("Event created successfully"), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("ERROR", ex);
            return new ResponseEntity<>(new CommonResponseError(), HttpStatus.BAD_REQUEST);
        }
    }

}
