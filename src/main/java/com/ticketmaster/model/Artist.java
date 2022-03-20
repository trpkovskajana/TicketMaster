package com.ticketmaster.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;


    /*public Artist() {
    }

    public Artist(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
    }*/
}
