package com.ticketmaster.service;

import com.ticketmaster.model.ShoppingCart;
import com.ticketmaster.model.Ticket;

import java.util.List;

public interface ShoppingCartService {

    List<Ticket> listAllTicketsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);

}
