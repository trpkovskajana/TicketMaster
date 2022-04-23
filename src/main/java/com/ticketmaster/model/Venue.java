package com.ticketmaster.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;

    private String city;

    private String address;

    public Venue() {
    }

    public Venue(String name, String city, String address) {
        Name = name;
        this.city = city;
        this.address = address;
    }
}
