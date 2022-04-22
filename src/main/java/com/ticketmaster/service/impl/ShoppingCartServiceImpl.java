package com.ticketmaster.service.impl;

import com.ticketmaster.exceptions.ShoppingCartNotFoundException;
import com.ticketmaster.exceptions.TicketAlreadyInShoppingCartException;
import com.ticketmaster.exceptions.TicketNotFoundException;
import com.ticketmaster.exceptions.UserNotFoundException;
import com.ticketmaster.model.*;
import com.ticketmaster.repository.ShoppingCartRepository;
import com.ticketmaster.repository.UserRepository;
import com.ticketmaster.service.EventService;
import com.ticketmaster.service.ShoppingCartService;
import com.ticketmaster.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final TicketService ticketService;
    private final EventService eventService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   TicketService ticketService, EventService eventService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.ticketService = ticketService;
        this.eventService = eventService;
    }

    @Override
    public List<Ticket> listAllTicketsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getTickets();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
<<<<<<< Updated upstream
    public ShoppingCart addProductToShoppingCart(String username, Long ticketId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Ticket ticket = this.ticketService.findById(ticketId);
        if(shoppingCart.getTickets()
                .stream().filter(i -> i.getId().equals(ticketId))
                .collect(Collectors.toList()).size() > 0)
            throw new TicketAlreadyInShoppingCartException(ticketId, username);
        shoppingCart.getTickets().add(ticket);
=======
    public ShoppingCart addTicketToShoppingCart(String username, Long eventId, int quantity) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Event event = this.eventService.findById(eventId);
                //.orElseThrow(() -> new TicketNotFoundException(ticketId));
        Ticket ticket = ticketService.create(event,quantity );

        if(shoppingCart.getTicketList()
                .stream().filter(i -> i.getEvent().equals(ticket.getEvent())).count() > 0)
            throw new TicketAlreadyInShoppingCartException(ticket.getId(), username);
        shoppingCart.getTicketList().add(ticket);
>>>>>>> Stashed changes
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart deleteTicket(String username,Long ticketId) {
        Ticket ticket = this.ticketService.findById(ticketId);
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        if(shoppingCart.getTicketList()
                .stream().filter(i -> i.getEvent().equals(ticket.getEvent())).count() > 0)
            shoppingCart.getTicketList().remove(ticket);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}

