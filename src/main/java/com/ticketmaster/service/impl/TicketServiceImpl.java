package com.ticketmaster.service.impl;

import com.ticketmaster.exceptions.TicketNotFoundException;
import com.ticketmaster.model.Event;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.repository.EventRepository;
import com.ticketmaster.repository.TicketRepository;
import com.ticketmaster.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public List<Ticket> listAll() {
        return this.ticketRepository.findAll();
    }

    @Override
    public Ticket findById(Long id) {
        return this.ticketRepository.findById(id).orElseThrow(TicketNotFoundException::new);
    }

    @Override
    public Ticket create(Event event, int quantity) {
        Event event1 = this.eventRepository.findById(event.getId()).orElseThrow(TicketNotFoundException::new);
        Ticket ticket = new Ticket(event1,quantity);
        return this.ticketRepository.save(ticket);
    }
/*
    @Override
    public Ticket update(Long id, Event event, int quantity) {
        Ticket ticket = this.findById(id);
        Event event1 = this.eventRepository.findById(event.getId()).orElseThrow(TicketNotFoundException(id));
        ticket.setEvent(event1);
        ticket.setQuantity(quantity);
        return this.ticketRepository.save(ticket);
    }
    */

    @Override
    public Ticket delete(Long id) {
        Ticket ticket = this.findById(id);
        this.ticketRepository.delete(ticket);
        return ticket;
    }


}
