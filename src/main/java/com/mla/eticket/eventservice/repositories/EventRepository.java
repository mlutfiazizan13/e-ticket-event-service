package com.mla.eticket.eventservice.repositories;

import com.mla.eticket.commonlib.repositories.GenericRepository;
import com.mla.eticket.eventservice.entities.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends GenericRepository<Event, Long> {
}
