package com.ticketmaster.service;

import com.ticketmaster.model.ShoppingCart;
import com.ticketmaster.model.Ticket;

import java.util.List;

public interface ShoppingCartService {
    List<Ticket> listAllTicketsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addTicketToShoppingCart(String username, Long eventId, int quantity);
    ShoppingCart deleteTicket(String username,Long ticketId);

}
