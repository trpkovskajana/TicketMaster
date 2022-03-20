package com.ticketmaster.repository;

import com.ticketmaster.model.Artist;
import com.ticketmaster.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
