package com.mla.eticket.eventservice.repositories;

import com.mla.eticket.commonlib.repositories.GenericRepository;
import com.mla.eticket.eventservice.entities.EventTicket;
import org.springframework.stereotype.Repository;

@Repository
public interface EventTicketRepository extends GenericRepository<EventTicket, Long> {
}
