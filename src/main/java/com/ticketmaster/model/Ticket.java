package com.ticketmaster.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    private Integer quantity;

    /*
    public Ticket(){}

    public Ticket(Event event, Integer quantity) {
        this.event = event;
        this.quantity = quantity;
    }
     */
}
