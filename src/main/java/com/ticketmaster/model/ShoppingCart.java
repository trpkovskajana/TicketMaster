package com.ticketmaster.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime dateCreated;

    @ManyToMany
    private List<Ticket> tickets;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.tickets = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}

