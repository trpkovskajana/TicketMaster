package com.ticketmaster.repository;

import com.ticketmaster.model.Artist;
import com.ticketmaster.model.ShoppingCart;
import com.ticketmaster.model.ShoppingCartStatus;
import com.ticketmaster.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
