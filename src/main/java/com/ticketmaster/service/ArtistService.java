package com.ticketmaster.service;

import com.ticketmaster.model.Artist;
import com.ticketmaster.model.Genre;
import com.ticketmaster.service.impl.ArtistServiceImpl;

import java.util.List;

public interface ArtistService {


    List<Artist> listAll();

    Artist findById(Long id);

    Artist create (String name, String url, String description, Genre genre);


}
