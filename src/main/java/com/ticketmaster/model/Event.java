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

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Artist> artists;

    @OneToOne
    private Venue venue;

    //TODO: ako stigneme da dodademe ovaa funkcionalnost
    //private Integer numOfTickets;

    public Event(){}

    public Event(LocalDate date, String name, Float duration, Float price, List<Artist> artists,Venue venue) {
        this.date = date;
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.artists = artists;
    }


}
