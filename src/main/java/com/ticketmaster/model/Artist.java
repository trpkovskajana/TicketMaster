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

    private String url;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;


    public Artist() {}


    public Artist(String name, String url, String description, Genre genre) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.genre = genre;
    }
}
