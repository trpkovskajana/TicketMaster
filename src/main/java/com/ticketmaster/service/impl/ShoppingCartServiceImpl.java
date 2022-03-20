package com.ticketmaster.service.impl;

import com.ticketmaster.exceptions.ShoppingCartNotFoundException;
import com.ticketmaster.exceptions.TicketAlreadyInShoppingCartException;
import com.ticketmaster.exceptions.TicketNotFoundException;
import com.ticketmaster.exceptions.UserNotFoundException;
import com.ticketmaster.model.ShoppingCart;
import com.ticketmaster.model.ShoppingCartStatus;
import com.ticketmaster.model.Ticket;
import com.ticketmaster.model.User;
import com.ticketmaster.repository.ShoppingCartRepository;
import com.ticketmaster.repository.UserRepository;
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

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   TicketService ticketService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.ticketService = ticketService;
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
    public ShoppingCart addProductToShoppingCart(String username, Long ticketId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Ticket ticket = this.ticketService.findById(ticketId);
        if(shoppingCart.getTickets()
                .stream().filter(i -> i.getId().equals(ticketId))
                .collect(Collectors.toList()).size() > 0)
            throw new TicketAlreadyInShoppingCartException(ticketId, username);
        shoppingCart.getTickets().add(ticket);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}

