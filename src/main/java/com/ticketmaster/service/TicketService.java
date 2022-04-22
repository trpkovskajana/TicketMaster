package com.ticketmaster.service;

import com.ticketmaster.model.Event;
import com.ticketmaster.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> listAll();

    Ticket findById(Long id);

    Ticket create (Event event, int quantity);

    Ticket update(Long id, Event event, int quantity);

<<<<<<< Updated upstream
    Ticket delete (Long id);
=======
     Ticket delete (Long id);
>>>>>>> Stashed changes


}
