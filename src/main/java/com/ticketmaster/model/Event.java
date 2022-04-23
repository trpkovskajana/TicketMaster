package com.ticketmaster.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String name;

    private Float duration;

    private Float price;

    private String url;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Artist> artists;

    @OneToOne
    private Venue venue;


    public Event(){}

    public Event(LocalDate date, String name, Float duration, Float price, String url, String description, List<Artist> artists,Venue venue) {
        this.date = date;
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.url=url;
        this.description = description;
        this.artists = artists;
        this.venue = venue;
    }


}